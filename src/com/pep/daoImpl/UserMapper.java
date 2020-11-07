package com.pep.daoImpl;

import java.sql.SQLException;
import java.sql.ResultSet;
import com.pep.pojo.UserRegistration;
import org.springframework.jdbc.core.RowMapper;

class UserMapper implements RowMapper<UserRegistration>
{
    UserMapper() {
        
    }
    
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