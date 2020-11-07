package com.pep.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pep.pojo.UserRegistration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import com.pep.dao.UserDao;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.stereotype.Controller;

@Controller
@SessionAttributes({ "noOfPages" })
public class RegisterController
{
    @Autowired
    private UserDao userDao;
    
    @RequestMapping({ "/signUp" })
    public ModelAndView redirect() {
        return new ModelAndView("userRegistration", "command", (Object)new UserRegistration());
    }
    
    @RequestMapping(value = { "/registerUser" }, method = { RequestMethod.POST })
    public ModelAndView registerUser(@ModelAttribute("TrackingApp") final UserRegistration userRegistration, ModelAndView mav) {
        final List<UserRegistration> empdetails = (List<UserRegistration>)this.userDao.retrieveEmpDetailsByEmpId(userRegistration.getEmpId());
        if (empdetails.isEmpty()) {
            this.userDao.registerUser(userRegistration);
            mav = new ModelAndView("index");
        }
        else {
            mav = new ModelAndView("userRegistration");
            mav.addObject("message", (Object)"The user was already Registered");
        }
        mav.addObject("command", (Object)new UserRegistration());
        return mav;
    }
    
    @RequestMapping(value = { "/export/{empId}" }, method = { RequestMethod.GET })
    public ModelAndView getExcel(final ModelAndView mav, @PathVariable final String empId) {
        final List<UserRegistration> empdetails = (List<UserRegistration>)this.userDao.retrieveEmpDetailsByEmpId(empId);
        mav.setViewName("EmployeeListExcel");
        mav.addObject("employeeList", (Object)empdetails);
        return mav;
    }
    
    @RequestMapping(value = { "/exportAll" }, method = { RequestMethod.GET })
    public ModelAndView getAllExcel(final ModelAndView mav) {
        final List<UserRegistration> empdetails = (List<UserRegistration>)this.userDao.retrieveEmpDetails();
        mav.addObject("employeeList", (Object)empdetails);
        mav.setViewName("EmployeeListExcel");
        return mav;
    }
    
    @RequestMapping(value = { "/viewRegEmpInfo" }, method = { RequestMethod.POST })
    public ModelAndView retrieveRegEmpInfo(@ModelAttribute("TrackingApp") final UserRegistration userRegistration, final ModelMap model, final ModelAndView mav) {
        final List<UserRegistration> empdetails = (List<UserRegistration>)this.userDao.retrieveEmpDetailsByEmpId(userRegistration.getEmpId());
        if (empdetails.isEmpty()) {
            mav.addObject("message", (Object)"No Records found for given EmployeeId");
        }
        mav.addObject("empdetails", (Object)empdetails);
        model.addAttribute("employeeList", (Object)empdetails);
        mav.setViewName("viewRegEmpDetails");
        return mav;
    }
    
    @RequestMapping(value = { "/viewAllRegEmployees" }, method = { RequestMethod.GET })
    public ModelAndView retrieveAllRegEmpInfo(final ModelMap model, final ModelAndView mav) {
        final List<UserRegistration> allEmpdetails = (List<UserRegistration>)this.userDao.retrieveEmpDetails();
        mav.addObject("empdetails", (Object)allEmpdetails);
        model.addAttribute("employeeList", (Object)allEmpdetails);
        mav.setViewName("viewRegEmpDetails");
        return mav;
    }
    
    @RequestMapping(value = { "/deleteEmpInfo/{empId}" }, method = { RequestMethod.GET })
    public ModelAndView deleteEmpInfo(@PathVariable final String empId, final ModelAndView mav) {
        this.userDao.deleteEmpDetails(empId);
        final List<UserRegistration> empdetails = (List<UserRegistration>)this.userDao.retrieveEmpDetails();
        mav.addObject("employeeList", (Object)empdetails);
        mav.setViewName("viewRegEmpDetails");
        mav.addObject("message", (Object)"The selected Employee has been Deleted");
        return mav;
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
    
    @RequestMapping(value = { "/retrieveEmpInfo/{empId}" }, method = { RequestMethod.GET })
    public ModelAndView retrieveEmpInfoById(UserRegistration userRegistration, @PathVariable final String empId, final ModelAndView mav) {
        final List<UserRegistration> empdetails = (List<UserRegistration>)this.userDao.retrieveEmpDetailsByEmpId(empId);
        if (empdetails != null) {
            userRegistration = empdetails.get(0);
        }
        mav.setViewName("editUserRegistration");
        mav.addObject("empdetails", (Object)userRegistration);
        mav.addObject("command", (Object)userRegistration);
        return mav;
    }
    
    @RequestMapping(value = { "/editEmpInfo" }, method = { RequestMethod.POST })
    public ModelAndView editEmpInfo(@ModelAttribute("TrackingApp") final UserRegistration userRegistration, final ModelAndView mav, final HttpServletRequest request) {
        this.userDao.editEmpInfo(userRegistration);
        final String admin = (String)request.getSession().getAttribute("isAdmin");
        if (admin.equals("Y")) {
            final List<UserRegistration> empdetails = (List<UserRegistration>)this.userDao.retrieveEmpDetails();
            mav.addObject("employeeList", (Object)empdetails);
            mav.setViewName("viewRegEmpDetails");
            mav.addObject("message", (Object)"The selected employee has been Updated");
        }
        else {
            final List<UserRegistration> empdetails = (List<UserRegistration>)this.userDao.retrieveEmpDetailsByEmpId(userRegistration.getEmpId());
            mav.addObject("employeeList", (Object)empdetails);
            mav.setViewName("viewRegEmpDetails");
            mav.addObject("message", (Object)"The selected employee has been Updated");
        }
        return mav;
    }
}