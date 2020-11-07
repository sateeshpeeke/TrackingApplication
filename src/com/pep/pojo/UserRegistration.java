package com.pep.pojo;

import java.sql.Date;

public class UserRegistration
{
    private int userid;
    private String firstName;
    private String lastName;
    private String empId;
    private String password;
    private String confirmPassword;
    private String reason;
    private Date startDate;
    private Date endDate;
    private String leaveType;
    private Date rosterDate;
    private String location;
    private String pickUp;
    private String dropTime;
    private long noOfDays;
    private String gpId;
    private String mobileNumber;
    private String role;
    private String tcsMail;
    private String pepsicoMail;
    private String primarySkils;
    private String secondarySkils;
    private String cluster;
    private String subCluster;
    private Date compoffWorkedDate;
    private String reportingTo;
    private String admin;
    private String newPwd;
    private String taskType;
    private String taskId;
    private String taskDescription;
    private String leaveStatus;
    private String comments;
    
    public UserRegistration(final String firstName, final String lastName, final String empId, final String gpId, final String mobileNumber, final String role, final String tcsMail, final String pepsicoMail, final String primarySkils, final String secondarySkils) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.empId = empId;
        this.gpId = gpId;
        this.mobileNumber = mobileNumber;
        this.role = role;
        this.tcsMail = tcsMail;
        this.pepsicoMail = pepsicoMail;
        this.primarySkils = primarySkils;
        this.secondarySkils = secondarySkils;
    }
    
    public UserRegistration() {
    }
    
    public int getUserid() {
        return this.userid;
    }
    
    public void setUserid(final int userid) {
        this.userid = userid;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmpId() {
        return this.empId;
    }
    
    public void setEmpId(final String empId) {
        this.empId = empId;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public String getConfirmPassword() {
        return this.confirmPassword;
    }
    
    public void setConfirmPassword(final String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }
    
    public String getReason() {
        return this.reason;
    }
    
    public void setReason(final String reason) {
        this.reason = reason;
    }
    
    public String getLeaveType() {
        return this.leaveType;
    }
    
    public void setLeaveType(final String leaveType) {
        this.leaveType = leaveType;
    }
    
    public Date getRosterDate() {
        return this.rosterDate;
    }
    
    public void setRosterDate(final Date rosterDate) {
        this.rosterDate = rosterDate;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(final String location) {
        this.location = location;
    }
    
    public String getPickUp() {
        return this.pickUp;
    }
    
    public void setPickUp(final String pickUp) {
        this.pickUp = pickUp;
    }
    
    public String getDropTime() {
        return this.dropTime;
    }
    
    public void setDropTime(final String dropTime) {
        this.dropTime = dropTime;
    }
    
    public long getNoOfDays() {
        return this.noOfDays;
    }
    
    public void setNoOfDays(final long noOfDays) {
        this.noOfDays = noOfDays;
    }
    
    public String getGpId() {
        return this.gpId;
    }
    
    public void setGpId(final String gpId) {
        this.gpId = gpId;
    }
    
    public String getMobileNumber() {
        return this.mobileNumber;
    }
    
    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    public String getRole() {
        return this.role;
    }
    
    public void setRole(final String role) {
        this.role = role;
    }
    
    public String getTcsMail() {
        return this.tcsMail;
    }
    
    public void setTcsMail(final String tcsMail) {
        this.tcsMail = tcsMail;
    }
    
    public String getPepsicoMail() {
        return this.pepsicoMail;
    }
    
    public void setPepsicoMail(final String pepsicoMail) {
        this.pepsicoMail = pepsicoMail;
    }
    
    public String getPrimarySkils() {
        return this.primarySkils;
    }
    
    public void setPrimarySkils(final String primarySkils) {
        this.primarySkils = primarySkils;
    }
    
    public String getSecondarySkils() {
        return this.secondarySkils;
    }
    
    public void setSecondarySkils(final String secondarySkils) {
        this.secondarySkils = secondarySkils;
    }
    
    public String getCluster() {
        return this.cluster;
    }
    
    public void setCluster(final String cluster) {
        this.cluster = cluster;
    }
    
    public String getSubCluster() {
        return this.subCluster;
    }
    
    public void setSubCluster(final String subCluster) {
        this.subCluster = subCluster;
    }
    
    public Date getCompoffWorkedDate() {
        return this.compoffWorkedDate;
    }
    
    public void setCompoffWorkedDate(final Date compoffWorkedDate) {
        this.compoffWorkedDate = compoffWorkedDate;
    }
    
    public String getReportingTo() {
        return this.reportingTo;
    }
    
    public void setReportingTo(final String reportingTo) {
        this.reportingTo = reportingTo;
    }
    
    public String getAdmin() {
        return this.admin;
    }
    
    public void setAdmin(final String admin) {
        this.admin = admin;
    }
    
    public String getNewPwd() {
        return this.newPwd;
    }
    
    public void setNewPwd(final String newPwd) {
        this.newPwd = newPwd;
    }
    
    public String getTaskType() {
        return this.taskType;
    }
    
    public void setTaskType(final String taskType) {
        this.taskType = taskType;
    }
    
    public String getTaskId() {
        return this.taskId;
    }
    
    public void setTaskId(final String taskId) {
        this.taskId = taskId;
    }
    
    public String getTaskDescription() {
        return this.taskDescription;
    }
    
    public void setTaskDescription(final String taskDescription) {
        this.taskDescription = taskDescription;
    }
    
    public String getLeaveStatus() {
        return this.leaveStatus;
    }
    
    public void setLeaveStatus(final String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }
    
    public String getComments() {
        return this.comments;
    }
    
    public void setComments(final String comments) {
        this.comments = comments;
    }
}