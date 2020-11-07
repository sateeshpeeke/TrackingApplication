package com.pep.controller;

import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import java.sql.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pep.pojo.UserRegistration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import com.pep.dao.LeavesDao;
import org.springframework.stereotype.Controller;

@Controller
public class LeaveController
{
    @Autowired
    private LeavesDao leavesDao;
    
    @RequestMapping({ "/home" })
    public ModelAndView home() {
        return new ModelAndView("loginSucces", "command", (Object)new UserRegistration());
    }
    
    @RequestMapping(value = { "/addLeave" }, method = { RequestMethod.POST })
    public ModelAndView addLeaves(@ModelAttribute("TrackingApp") final UserRegistration userRegistration, final ModelAndView mav) {
        final Date date1 = userRegistration.getStartDate();
        final Date date2 = userRegistration.getEndDate();
        final long diff = date2.getTime() - date1.getTime();
        long noOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        if (noOfDays == 0L) {
            noOfDays = 1L;
        }
        else {
            ++noOfDays;
        }
        userRegistration.setNoOfDays(noOfDays);
        userRegistration.setLeaveStatus("Pending");
        if (userRegistration.getReason() == null) {
            userRegistration.setReason("null");
        }
        this.leavesDao.addLeave(userRegistration);
        final List<UserRegistration> leavesdetails = (List<UserRegistration>)this.leavesDao.retrieveAllLeaveDetails();
        mav.addObject("leavesdetails", (Object)leavesdetails);
        mav.setViewName("viewLeaves");
        return mav;
    }
    
    @RequestMapping(value = { "/viewLeaves" }, method = { RequestMethod.POST })
    public ModelAndView retrieveLeavesByEmpId(@ModelAttribute("TrackingApp") final UserRegistration userRegistration, final ModelAndView mav) {
        final List<UserRegistration> leavesdetails = (List<UserRegistration>)this.leavesDao.retrieveLeaveDetailsByEmpId(userRegistration.getEmpId());
        if (leavesdetails.isEmpty()) {
            mav.addObject("message", (Object)"No Records found for given EmployeeId");
        }
        mav.addObject("leavesdetails", (Object)leavesdetails);
        mav.setViewName("viewLeaves");
        return mav;
    }
    
    @RequestMapping(value = { "/viewAllLeaves" }, method = { RequestMethod.GET })
    public ModelAndView retrieveAllLeaves(final ModelAndView mav) {
        final List<UserRegistration> leavesdetails = (List<UserRegistration>)this.leavesDao.retrieveAllLeaveDetails();
        mav.addObject("leavesdetails", (Object)leavesdetails);
        mav.setViewName("viewLeaves");
        return mav;
    }
    
    @RequestMapping(value = { "/retrieveLeave/{id}" }, method = { RequestMethod.GET })
    public ModelAndView retrieveLeave(UserRegistration userRegistration, @PathVariable final int id, final ModelAndView mav) {
        final List<UserRegistration> leavesdetails = (List<UserRegistration>)this.leavesDao.retrieveLeaveDetails(id);
        if (leavesdetails != null) {
            userRegistration = leavesdetails.get(0);
            mav.addObject("leaveStatuss", (Object)userRegistration.getLeaveStatus());
            mav.addObject("leaveTypes", (Object)userRegistration.getLeaveType());
            mav.addObject("reason1", (Object)userRegistration.getReason());
        }
        mav.setViewName("EditLeaves");
        mav.addObject("leavesdetails", (Object)userRegistration);
        mav.addObject("command", (Object)userRegistration);
        return mav;
    }
    
    @ModelAttribute("leaveTypeList")
    public List<String> getleaveTypeList() {
        final List<String> leaveTypeList = new ArrayList<String>();
        leaveTypeList.add("Casual leave");
        leaveTypeList.add("Sick leave");
        leaveTypeList.add("comp-offs");
        leaveTypeList.add("Flexi-leave");
        leaveTypeList.add("WFH");
        leaveTypeList.add("Weekend-support");
        leaveTypeList.add("Holliday-support");
        return leaveTypeList;
    }
    
    @ModelAttribute("leaveStatusList")
    public List<String> getleaveStatusList() {
        final List<String> leaveStatusList = new ArrayList<String>();
        leaveStatusList.add("Pending");
        leaveStatusList.add("Accepted");
        leaveStatusList.add("Rejected");
        return leaveStatusList;
    }
    
    @ModelAttribute("clusterList")
    public List<String> getClusterList() {
        final List<String> clusterList = new ArrayList<String>();
        clusterList.add("Web and Digital");
        clusterList.add("Integration");
        clusterList.add("FLNA");
        clusterList.add("Latam-legacy");
        clusterList.add("SAP Enterprise");
        clusterList.add("AMENA Non-Enterprise?");
        return clusterList;
    }
    
    @ModelAttribute("subClusterList")
    public List<String> getSubClusterList() {
        final List<String> subClusterList = new ArrayList<String>();
        subClusterList.add("Portal and Collaboration");
        subClusterList.add("Imaging");
        subClusterList.add("Security");
        subClusterList.add("MQ Services");
        subClusterList.add("Informatica");
        subClusterList.add("EDW");
        subClusterList.add("Layer7 API");
        subClusterList.add("ECG");
        subClusterList.add("TIBCO");
        subClusterList.add("RPA and UI Tools");
        subClusterList.add("WebLogi and Apache");
        subClusterList.add("GEC");
        return subClusterList;
    }
    
    @RequestMapping(value = { "/editLeave" }, method = { RequestMethod.POST })
    public ModelAndView editLeaves(@ModelAttribute("TrackingApp") final UserRegistration userRegistration, final ModelAndView mav, final HttpServletRequest request) {
        final Date compOffdate = userRegistration.getCompoffWorkedDate();
        final Date date1 = userRegistration.getStartDate();
        final Date date2 = userRegistration.getEndDate();
        final long diff = date2.getTime() - date1.getTime();
        long noOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        if (noOfDays == 0L) {
            noOfDays = 1L;
        }
        else {
            ++noOfDays;
        }
        userRegistration.setNoOfDays(noOfDays);
        if (compOffdate == null) {
            userRegistration.setCompoffWorkedDate(new Date(0L));
        }
        System.out.println("status1 = " + userRegistration.getLeaveStatus());
        this.leavesDao.editLeave(userRegistration);
        final String admin = (String)request.getSession().getAttribute("isAdmin");
        if (admin.equals("Y")) {
            final List<UserRegistration> leavesdetails = (List<UserRegistration>)this.leavesDao.retrieveAllLeaveDetails();
            mav.addObject("leavesdetails", (Object)leavesdetails);
            mav.setViewName("viewLeaves");
            mav.addObject("message", (Object)"The selected leave has been Updated");
        }
        else {
            final List<UserRegistration> leavesdetails = (List<UserRegistration>)this.leavesDao.retrieveLeaveDetailsByEmpId(userRegistration.getEmpId());
            mav.addObject("leavesdetails", (Object)leavesdetails);
            mav.setViewName("viewLeaves");
            mav.addObject("message", (Object)"The selected leave has been Updated");
        }
        return mav;
    }
    
    @RequestMapping(value = { "/deleteLeaves/{userid}" }, method = { RequestMethod.GET })
    public ModelAndView deleteLeaves(@PathVariable final int userid, final ModelAndView mav) {
        this.leavesDao.deleteLeave(userid);
        final List<UserRegistration> leavesdetails = (List<UserRegistration>)this.leavesDao.retrieveAllLeaveDetails();
        mav.addObject("leavesdetails", (Object)leavesdetails);
        mav.setViewName("viewLeaves");
        mav.addObject("message", (Object)"The selected leave has been Deleted");
        return mav;
    }
    
    @RequestMapping(value = { "/exportLeave/{empId}" }, method = { RequestMethod.GET })
    public ModelAndView getExcel(final ModelAndView mav, @PathVariable final String empId) {
        final List<UserRegistration> empLeavedetails = (List<UserRegistration>)this.leavesDao.retrieveLeaveDetailsByEmpId(empId);
        mav.setViewName("EmployeeLeavesListExcel");
        mav.addObject("employeeLeaveList", (Object)empLeavedetails);
        return mav;
    }
    
    @RequestMapping(value = { "/exportAllLeaves" }, method = { RequestMethod.GET })
    public ModelAndView getAllExcel(final ModelAndView mav) {
        final List<UserRegistration> empAllLeavedetails = (List<UserRegistration>)this.leavesDao.retrieveAllLeaveDetails();
        mav.setViewName("EmployeeLeavesListExcel");
        mav.addObject("employeeLeaveList", (Object)empAllLeavedetails);
        return mav;
    }
    
    @RequestMapping(value = { "/leavecalender" }, method = { RequestMethod.GET })
    @ResponseBody
    public String leave(final HttpServletResponse response) {
        final List<UserRegistration> leavesdetails = (List<UserRegistration>)this.leavesDao.retrieveLeaveDetailsByEmpId("3");
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", leavesdetails.get(0).getEmpId());
        map.put("title", leavesdetails.get(0).getLeaveType());
        map.put("start", leavesdetails.get(0).getStartDate());
        map.put("end", leavesdetails.get(0).getEndDate());
        final String json = null;
        System.out.println(json);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        return json;
    }
}