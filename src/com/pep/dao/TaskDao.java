package com.pep.dao;

import javax.sql.DataSource;
import java.util.List;
import com.pep.pojo.UserRegistration;

public interface TaskDao
{
    String addtask(final UserRegistration p0);
    
    List<UserRegistration> retrieveAllTasksDetails();
    
    List<UserRegistration> retrieveTaskDetailsByEmpId(final String p0);
    
    List<UserRegistration> retrieveTaskDetails(final int p0);
    
    int editTask(final UserRegistration p0);
    
    int deleteTask(final int p0);
    
    void setDataSource(final DataSource p0);
}