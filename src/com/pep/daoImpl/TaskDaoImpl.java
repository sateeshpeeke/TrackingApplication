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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.pep.dao.TaskDao;

@Repository
@Component
public class TaskDaoImpl implements TaskDao
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
    public String addtask(final UserRegistration user) {
        final String fName = user.getFirstName();
        final String lName = user.getLastName();
        final String empId = user.getEmpId();
        final String cluster = user.getCluster();
        final String subCluster = user.getSubCluster();
        final Date shiftDate = user.getStartDate();
        final String shiftStartTime = user.getPickUp();
        final String shiftEndTime = user.getDropTime();
        final String taskType = user.getTaskType();
        final String taskId = user.getTaskId();
        final String tDescription = user.getTaskDescription();
        final String SQL = "insert into addTask(firstName,lastName,employeeId,cluster,subCluster,shiftDate,shiftStartTime,shiftEndTime,taskType, taskId,taskDescription)  values (?, ?, ?, ?,?,?,?,?,?,?,?)";
        this.userDaoJDBCTemplate.update(SQL, new Object[] { fName, lName, empId, cluster, subCluster, shiftDate, shiftStartTime, shiftEndTime, taskType, taskId, tDescription });
        return null;
    }
    
    @Override
    public List<UserRegistration> retrieveAllTasksDetails() {
        final String SQL = "select * from  addTask";
        final List<UserRegistration> users = (List<UserRegistration>)this.userDaoJDBCTemplate.query(SQL, (RowMapper)new UserMapper());
        return users;
    }
    
    @Override
    public List<UserRegistration> retrieveTaskDetailsByEmpId(final String empId) {
        final String SQL = "select * from  addTask where employeeId='" + empId + "'";
        final List<UserRegistration> users = (List<UserRegistration>)this.userDaoJDBCTemplate.query(SQL, (RowMapper)new UserMapper());
        return users;
    }
    
    @Override
    public int deleteTask(final int userId) {
        final String sql = "delete from addTask where userid =" + userId + "";
        return this.userDaoJDBCTemplate.update(sql);
    }
    
    @Override
    public List<UserRegistration> retrieveTaskDetails(final int id) {
        final String SQL = "select * from  addTask where userid=" + id + "";
        final List<UserRegistration> users = (List<UserRegistration>)this.userDaoJDBCTemplate.query(SQL, (RowMapper)new UserMapper());
        return users;
    }
    
    @Override
    public int editTask(final UserRegistration user) {
        final String sql = "update addTask set firstName='" + user.getFirstName() + "', lastName = '" + user.getLastName() + "', employeeId='" + user.getEmpId() + "',shiftDate ='" + user.getStartDate() + "',shiftStartTime='" + user.getPickUp() + "',shiftEndTime='" + user.getDropTime() + "',taskType='" + user.getTaskType() + "',taskId='" + user.getTaskId() + "', taskDescription = '" + user.getTaskDescription() + "',cluster='" + user.getCluster() + "',subCluster='" + user.getSubCluster() + "' where userid=" + user.getUserid() + "";
        return this.userDaoJDBCTemplate.update(sql);
    }
    
    class UserMapper implements RowMapper<UserRegistration>
    {
        public UserRegistration mapRow(final ResultSet rs, final int arg1) throws SQLException {
            final UserRegistration user = new UserRegistration();
            user.setUserid(rs.getInt("userid"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setEmpId(rs.getString("employeeId"));
            user.setCluster(rs.getString("cluster"));
            user.setSubCluster(rs.getString("subCluster"));
            user.setStartDate(rs.getDate("shiftDate"));
            user.setPickUp(rs.getString("shiftStartTime"));
            user.setDropTime(rs.getString("shiftEndTime"));
            user.setTaskType(rs.getString("taskType"));
            user.setTaskId(rs.getString("taskId"));
            user.setTaskDescription(rs.getString("taskDescription"));
            return user;
        }
    }
}