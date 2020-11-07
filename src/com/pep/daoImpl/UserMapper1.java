package com.pep.daoImpl;

import java.sql.SQLException;
import java.sql.ResultSet;
import com.pep.pojo.UserRegistration;
import org.springframework.jdbc.core.RowMapper;

class UserMapper1 implements RowMapper<UserRegistration>
{
    UserMapper1() {
        //this.this$0 = this$0;
    }
    
    public UserRegistration mapRow(final ResultSet rs, final int arg1) throws SQLException {
        final UserRegistration user = new UserRegistration();
        //user.setUserid(rs.getInt("userid"));
        //user.setFirstName(rs.getString("firstName"));
        //user.setLastName(rs.getString("lastName"));
        //user.setLeaveType(rs.getString("leaveType"));
        user.setEmpId(rs.getString("employeeId"));
        user.setPepsicoMail("pepsicoMail");
        user.setAdmin("admin");
        //user.setCluster(rs.getString("cluster"));
        //user.setSubCluster(rs.getString("subCluster"));
        //user.setStartDate(rs.getDate("startDate"));
        //user.setEndDate(rs.getDate("endDate"));
        //user.setReason(rs.getString("reason"));
        //user.setNoOfDays(rs.getLong("noOfDays"));
        //user.setCompoffWorkedDate(rs.getDate("compoffWorkedDate"));
       // user.setLeaveStatus(rs.getString("leaveStatus"));
        //user.setComments(rs.getString("comments"));
        return user;
    }
}