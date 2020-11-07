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
import org.springframework.stereotype.Repository;
import com.pep.dao.LeavesDao;

@Repository
public class LeavesDaoImpl implements LeavesDao
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
    public String addLeave(final UserRegistration user) {
        final String fName = user.getFirstName();
        final String lName = user.getLastName();
        final String empId = user.getEmpId();
        final String ltype = user.getLeaveType();
        final Date sDate = user.getStartDate();
        final Date eDate = user.getEndDate();
        final String reason = user.getReason();
        final long noOfDays = user.getNoOfDays();
        final String cluster = user.getCluster();
        final String subCluster = user.getSubCluster();
        final Date compDate = user.getCompoffWorkedDate();
        final String leaveStatus = user.getLeaveStatus();
        final String SQL = "insert into addLeaves(firstName,lastName,employeeId,leaveType,startDate,endDate,reason,noOfDays,cluster,subCluster,compoffWorkedDate, leaveStatus)  values (?,?, ?, ?, ?,?,?,?,?,?,?,?)";
        this.userDaoJDBCTemplate.update(SQL, new Object[] { fName, lName, empId, ltype, sDate, eDate, reason, noOfDays, cluster, subCluster, compDate, leaveStatus });
        return null;
    }
    
    @Override
    public List<UserRegistration> retrieveLeaveDetails(final int id) {
        final String SQL = "select * from  addLeaves where userid=" + id + "";
        final List<UserRegistration> users = (List<UserRegistration>)this.userDaoJDBCTemplate.query(SQL, (RowMapper)new UserMapper());
        return users;
    }
    
    @Override
    public List<UserRegistration> retrieveLeaveDetailsByEmpId(final String empid) {
        final String SQL = "select * from  addLeaves where employeeId='" + empid + "'";
        final List<UserRegistration> users = (List<UserRegistration>)this.userDaoJDBCTemplate.query(SQL, (RowMapper)new UserMapper());
        return users;
    }
    
    @Override
    public int editLeave(final UserRegistration user) {
        final String sql = "update addLeaves set firstName='" + user.getFirstName() + "', lastName = '" + user.getLastName() + "', employeeId='" + user.getEmpId() + "',startDate ='" + user.getStartDate() + "',endDate='" + user.getEndDate() + "',leaveType='" + user.getLeaveType() + "',reason='" + user.getReason() + "', noOfDays = " + user.getNoOfDays() + ",cluster='" + user.getCluster() + "',subCluster='" + user.getSubCluster() + "',compoffWorkedDate ='" + user.getCompoffWorkedDate() + "',leaveStatus='" + user.getLeaveStatus() + "',comments='" + user.getComments() + "' where userid=" + user.getUserid() + "";
        return this.userDaoJDBCTemplate.update(sql);
    }
    
    @Override
    public int deleteLeave(final int userId) {
        final String sql = "delete from addLeaves where userid =" + userId + "";
        return this.userDaoJDBCTemplate.update(sql);
    }
    
    @Override
    public List<UserRegistration> retrieveAllLeaveDetails() {
        final String SQL = "select * from  addLeaves";
        final List<UserRegistration> users = (List<UserRegistration>)this.userDaoJDBCTemplate.query(SQL, (RowMapper)new UserMapper());
        return users;
    }
    
    class UserMapper implements RowMapper<UserRegistration>
    {
        public UserRegistration mapRow(final ResultSet rs, final int arg1) throws SQLException {
            final UserRegistration user = new UserRegistration();
            user.setUserid(rs.getInt("userid"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setLeaveType(rs.getString("leaveType"));
            user.setEmpId(rs.getString("employeeId"));
            user.setCluster(rs.getString("cluster"));
            user.setSubCluster(rs.getString("subCluster"));
            user.setStartDate(rs.getDate("startDate"));
            user.setEndDate(rs.getDate("endDate"));
            user.setReason(rs.getString("reason"));
            user.setNoOfDays(rs.getLong("noOfDays"));
            user.setCompoffWorkedDate(rs.getDate("compoffWorkedDate"));
            user.setLeaveStatus(rs.getString("leaveStatus"));
            user.setComments(rs.getString("comments"));
            return user;
        }
    }
}