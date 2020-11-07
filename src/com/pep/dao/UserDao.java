package com.pep.dao;

import java.util.List;
import javax.sql.DataSource;
import com.pep.pojo.UserRegistration;

public interface UserDao
{
    String registerUser(final UserRegistration p0);
    
    UserRegistration loginUser(final UserRegistration p0);
    
    UserRegistration forgotPwd(final UserRegistration p0);
    
    void setDataSource(final DataSource p0);
    
    int changePwd(final UserRegistration p0);
    
    int editEmpInfo(final UserRegistration p0);
    
    int deleteEmpDetails(final String p0);
    
    List<UserRegistration> retrieveEmpDetailsByEmpId(final String p0);
    
    List<UserRegistration> retrieveEmpDetails();
    
    List<UserRegistration> retrieveEmpDetails(final int p0, final int p1);
}