package com.pep.dao;

import java.util.List;
import javax.sql.DataSource;
import com.pep.pojo.UserRegistration;

public interface LeavesDao
{
    String addLeave(final UserRegistration p0);
    
    int editLeave(final UserRegistration p0);
    
    int deleteLeave(final int p0);
    
    void setDataSource(final DataSource p0);
    
    List<UserRegistration> retrieveLeaveDetails(final int p0);
    
    List<UserRegistration> retrieveLeaveDetailsByEmpId(final String p0);
    
    List<UserRegistration> retrieveAllLeaveDetails();
}