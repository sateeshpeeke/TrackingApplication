package com.pep.dao;

import java.util.List;
import javax.sql.DataSource;
import com.pep.pojo.UserRegistration;

public interface AdhocDao
{
    String addAdhoc(final UserRegistration p0);
    
    void setDataSource(final DataSource p0);
    
    List<UserRegistration> retrieveRosterDetailsByEmpId(final String p0);
    
    List<UserRegistration> retrieveAllRosterDetails();
    
    int deleteRoster(final int p0);
    
    List<UserRegistration> retrieveRosterDetails(final int p0);
    
    int editRoster(final UserRegistration p0);
}