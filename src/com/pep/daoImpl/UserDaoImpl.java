package com.pep.daoImpl;

import javax.sql.DataSource;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import com.pep.pojo.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.pep.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao
{
    @Autowired
    private JdbcTemplate userDaoJDBCTemplate;
    
    @Override
    public String registerUser(final UserRegistration user) {
        final String fName = user.getFirstName();
        final String lName = user.getLastName();
        final String empId = user.getEmpId();
        final String pass = user.getPassword();
        final String gpId = user.getGpId();
        final String mNumber = user.getMobileNumber();
        final String role = user.getRole();
        final String tcsMail = user.getTcsMail();
        final String pMail = user.getPepsicoMail();
        final String pKills = user.getPrimarySkils();
        final String sSkills = user.getSecondarySkils();
        final String cluster = user.getCluster();
        final String subClusetr = user.getSubCluster();
        final String reportingTo = user.getReportingTo();
        final String admin = user.getAdmin();
        final String SQL = "insert into UserRegistration(firstName,lastName,employeeId,gpid,mobileNumber,role,tcsMail,pepsicoMail,primarySkills,secondarySkills,password,cluster,subcluster,reportingTo, admin)  values (?, ?, ?,?, ?,?,?,?,?,?,?,?,?,?,?)";
        this.userDaoJDBCTemplate.update(SQL, new Object[] { fName, lName, empId, gpId, mNumber, role, tcsMail, pMail, pKills, sSkills, pass, cluster, subClusetr, reportingTo, admin });
        return null;
    }
    
    @Override
    public UserRegistration loginUser(final UserRegistration user) {
        final String SQL = "select pepsicoMail,employeeId,admin from  UserRegistration where employeeId= '" + user.getEmpId() + "' and password= '" + user.getPassword() + "'";
        final List<UserRegistration> users = (List<UserRegistration>)this.userDaoJDBCTemplate.query(SQL, (RowMapper)new UserMapper1());
        return (users.size() > 0) ? users.get(0) : null;
    }
    
    @Override
    public void setDataSource(final DataSource dataSource) {
        this.userDaoJDBCTemplate = new JdbcTemplate(dataSource);
    }
    
    public void setUserDaoJDBCTemplate(final JdbcTemplate userDaoJDBCTemplate) {
        this.userDaoJDBCTemplate = userDaoJDBCTemplate;
    }
    
    @Override
    public List<UserRegistration> retrieveEmpDetailsByEmpId(final String empid) {
        final String SQL = "select * from  userRegistration where employeeId='" + empid + "'";
        final List<UserRegistration> users = (List<UserRegistration>)this.userDaoJDBCTemplate.query(SQL, (RowMapper)new UserMapper1());
        return users;
    }
    
    @Override
    public List<UserRegistration> retrieveEmpDetails() {
        final String SQL = "select * from  userRegistration";
        final List<UserRegistration> users = (List<UserRegistration>)this.userDaoJDBCTemplate.query(SQL, (RowMapper)new UserMapper1());
        return users;
    }
    
    @Override
    public int changePwd(final UserRegistration user) {
        final String SQL = "update userRegistration set password='" + user.getNewPwd() + "' where employeeId='" + user.getEmpId() + "'";
        return this.userDaoJDBCTemplate.update(SQL);
    }
    
    @Override
    public List<UserRegistration> retrieveEmpDetails(final int pageId, final int total) {
        final String SQL = "select * from  userRegistration limit " + (pageId - 1) + "," + total;
        final List<UserRegistration> users = (List<UserRegistration>)this.userDaoJDBCTemplate.query(SQL, (RowMapper)new UserMapper1());
        return users;
    }
    
    @Override
    public int editEmpInfo(final UserRegistration user) {
        final String sql = "update userRegistration set firstName='" + user.getFirstName() + "', lastName = '" + user.getLastName() + "', employeeId='" + user.getEmpId() + "',gpId ='" + user.getGpId() + "',mobileNumber='" + user.getMobileNumber() + "',tcsMail='" + user.getTcsMail() + "',pepsicoMail='" + user.getPepsicoMail() + "', reportingTo = '" + user.getReportingTo() + "',cluster='" + user.getCluster() + "',subCluster='" + user.getSubCluster() + "',role ='" + user.getRole() + "',primarySkills='" + user.getPrimarySkils() + "',secondarySkills='" + user.getSecondarySkils() + "',admin='" + user.getAdmin() + "' where employeeId='" + user.getEmpId() + "'";
        return this.userDaoJDBCTemplate.update(sql);
    }
    
    @Override
    public int deleteEmpDetails(final String empId) {
        final String sql = "delete from userRegistration where employeeId ='" + empId + "'";
        return this.userDaoJDBCTemplate.update(sql);
    }
    
    @Override
    public UserRegistration forgotPwd(final UserRegistration user) {
        final String SQL = "select * from  UserRegistration where employeeId= '" + user.getEmpId() + "'";
        final List<UserRegistration> users = (List<UserRegistration>)this.userDaoJDBCTemplate.query(SQL, (RowMapper)new UserMapper());
        return (users.size() > 0) ? users.get(0) : null;
    }
}