package com.pep.daoImpl;

import java.sql.SQLException;
import java.sql.ResultSet;
import org.springframework.jdbc.core.RowMapper;
import java.util.List;
import java.sql.Date;
import com.pep.pojo.UserRegistration;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.pep.dao.AdhocDao;

public class AdhocDaoImpl implements AdhocDao
{
    @Autowired
    private JdbcTemplate userDaoJDBCTemplate;
    
    public void setUserDaoJDBCTemplate(final JdbcTemplate userDaoJDBCTemplate) {
        this.userDaoJDBCTemplate = userDaoJDBCTemplate;
    }
    
    @Override
    public void setDataSource(final DataSource dataSource) {
        this.userDaoJDBCTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public String addAdhoc(final UserRegistration user) {
        final String fName = user.getFirstName();
        final String lName = user.getLastName();
        final String empId = user.getEmpId();
        final Date rDate = user.getRosterDate();
        final String pickup = user.getPickUp();
        final String drop = user.getDropTime();
        final String location = user.getLocation();
        final String SQL = "insert into addhoc(firstName,lastName,employeeId,rosterDate,pickUp,droptime,location)  values (?, ?, ?, ?,?,?,?)";
        this.userDaoJDBCTemplate.update(SQL, new Object[] { fName, lName, empId, rDate, pickup, drop, location });
        return null;
    }
    
    @Override
    public List<UserRegistration> retrieveRosterDetailsByEmpId(final String empid) {
        final String SQL = "select * from  addhoc where employeeId='" + empid + "'";
        final List<UserRegistration> users = (List<UserRegistration>)this.userDaoJDBCTemplate.query(SQL, (RowMapper)new UserMapper());
        return users;
    }
    
    @Override
    public List<UserRegistration> retrieveAllRosterDetails() {
        final String SQL = "select * from  addhoc";
        final List<UserRegistration> users = (List<UserRegistration>)this.userDaoJDBCTemplate.query(SQL, (RowMapper)new UserMapper());
        return users;
    }
    
    @Override
    public int deleteRoster(final int userid) {
        final String sql = "delete from addhoc where userid =" + userid + "";
        return this.userDaoJDBCTemplate.update(sql);
    }
    
    @Override
    public List<UserRegistration> retrieveRosterDetails(final int id) {
        final String SQL = "select * from  addhoc where userid=" + id + "";
        final List<UserRegistration> users = (List<UserRegistration>)this.userDaoJDBCTemplate.query(SQL, (RowMapper)new UserMapper());
        return users;
    }
    
    @Override
    public int editRoster(final UserRegistration user) {
        final String sql = "update addhoc set firstName='" + user.getFirstName() + "', lastName = '" + user.getLastName() + "', employeeId='" + user.getEmpId() + "',rosterDate ='" + user.getRosterDate() + "',pickUp='" + user.getPickUp() + "',dropTime='" + user.getDropTime() + "',location='" + user.getLocation() + "' where userid=" + user.getUserid() + "";
        return this.userDaoJDBCTemplate.update(sql);
    }
    
    class UserMapper implements RowMapper<UserRegistration>
    {
        public UserRegistration mapRow(final ResultSet rs, final int arg1) throws SQLException {
            final UserRegistration user = new UserRegistration();
            user.setUserid(rs.getInt("userid"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setRosterDate(rs.getDate("rosterDate"));
            user.setEmpId(rs.getString("employeeId"));
            user.setPickUp(rs.getString("pickUp"));
            user.setDropTime(rs.getString("dropTime"));
            user.setLocation(rs.getString("location"));
            return user;
        }
    }
}