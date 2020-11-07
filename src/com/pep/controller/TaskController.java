package com.pep.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.pep.pojo.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import com.pep.dao.TaskDao;
import org.springframework.stereotype.Controller;

@Controller
public class TaskController
{
    @Autowired
    private TaskDao taskDao;
    
    @RequestMapping(value = { "/addTask" }, method = { RequestMethod.POST })
    public ModelAndView addTask(@ModelAttribute("TrackingApp") final UserRegistration userRegistration, final ModelAndView mav) {
        this.taskDao.addtask(userRegistration);
        final List<UserRegistration> tasksDetails = (List<UserRegistration>)this.taskDao.retrieveAllTasksDetails();
        mav.addObject("tasksDetails", (Object)tasksDetails);
        mav.setViewName("viewTasks");
        return mav;
    }
    
    @RequestMapping(value = { "/viewTask" }, method = { RequestMethod.POST })
    public ModelAndView retrieveTasksByEmpId(@ModelAttribute("TrackingApp") final UserRegistration userRegistration, final ModelAndView mav) {
        final List<UserRegistration> tasksDetails = (List<UserRegistration>)this.taskDao.retrieveTaskDetailsByEmpId(userRegistration.getEmpId());
        if (tasksDetails.isEmpty()) {
            mav.addObject("message", (Object)"No Records found for given EmployeeId");
        }
        mav.addObject("tasksDetails", (Object)tasksDetails);
        mav.setViewName("viewTasks");
        return mav;
    }
    
    @RequestMapping(value = { "/viewAllTasks" }, method = { RequestMethod.GET })
    public ModelAndView retrieveAllTasks(final ModelAndView mav) {
        final List<UserRegistration> tasksDetails = (List<UserRegistration>)this.taskDao.retrieveAllTasksDetails();
        mav.addObject("tasksDetails", (Object)tasksDetails);
        mav.setViewName("viewTasks");
        return mav;
    }
    
    @RequestMapping(value = { "/deleteTasks/{userid}" }, method = { RequestMethod.GET })
    public ModelAndView deleteTasks(@PathVariable final int userid, final ModelAndView mav) {
        this.taskDao.deleteTask(userid);
        final List<UserRegistration> tasksDetails = (List<UserRegistration>)this.taskDao.retrieveAllTasksDetails();
        mav.addObject("tasksDetails", (Object)tasksDetails);
        mav.setViewName("viewTasks");
        mav.addObject("message", (Object)"The selected task has been Deleted");
        return mav;
    }
    
    @RequestMapping(value = { "/exportTask/{empId}" }, method = { RequestMethod.GET })
    public ModelAndView getExcel(final ModelAndView mav, @PathVariable final String empId) {
        final List<UserRegistration> empTaskdetails = (List<UserRegistration>)this.taskDao.retrieveTaskDetailsByEmpId(empId);
        mav.setViewName("EmployeeTasksListExcel");
        mav.addObject("employeeTaskList", (Object)empTaskdetails);
        return mav;
    }
    
    @RequestMapping(value = { "/exportAllTasks" }, method = { RequestMethod.GET })
    public ModelAndView getAllExcel(final ModelAndView mav) {
        final List<UserRegistration> empAllTaskdetails = (List<UserRegistration>)this.taskDao.retrieveAllTasksDetails();
        mav.setViewName("EmployeeTasksListExcel");
        mav.addObject("employeeTaskList", (Object)empAllTaskdetails);
        return mav;
    }
    
    @RequestMapping(value = { "/retrieveTask/{id}" }, method = { RequestMethod.GET })
    public ModelAndView retrieveTask(UserRegistration userRegistration, @PathVariable final int id, final ModelAndView mav) {
        final List<UserRegistration> tasksDetails = (List<UserRegistration>)this.taskDao.retrieveTaskDetails(id);
        if (tasksDetails != null) {
            userRegistration = tasksDetails.get(0);
        }
        mav.setViewName("EditTasks");
        mav.addObject("tasksDetails", (Object)userRegistration);
        mav.addObject("command", (Object)userRegistration);
        return mav;
    }
    
    @RequestMapping(value = { "/editTask" }, method = { RequestMethod.POST })
    public ModelAndView editLeaves(@ModelAttribute("TrackingApp") final UserRegistration userRegistration, final ModelAndView mav, final HttpServletRequest request) {
        this.taskDao.editTask(userRegistration);
        final String admin = (String)request.getSession().getAttribute("isAdmin");
        if (admin.equals("Y")) {
            final List<UserRegistration> tasksDetails = (List<UserRegistration>)this.taskDao.retrieveAllTasksDetails();
            mav.addObject("tasksDetails", (Object)tasksDetails);
            mav.setViewName("viewTasks");
            mav.addObject("message", (Object)"The selected Task has been Updated");
        }
        else {
            final List<UserRegistration> tasksDetails = (List<UserRegistration>)this.taskDao.retrieveTaskDetailsByEmpId(userRegistration.getEmpId());
            mav.addObject("tasksDetails", (Object)tasksDetails);
            mav.setViewName("viewTasks");
            mav.addObject("message", (Object)"The selected Task has been Updated");
        }
        return mav;
    }
    
    @ModelAttribute("taskTypeList")
    public List<String> getTaskTypeList() {
        final List<String> taskTypeList = new ArrayList<String>();
        taskTypeList.add("Incident");
        taskTypeList.add("Service Request");
        return taskTypeList;
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
}