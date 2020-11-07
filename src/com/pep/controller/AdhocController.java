package com.pep.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.pep.pojo.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import com.pep.dao.AdhocDao;
import org.springframework.stereotype.Controller;

@Controller
public class AdhocController
{
    @Autowired
    private AdhocDao addhocDao;
    
    @RequestMapping(value = { "/addAdhoc" }, method = { RequestMethod.POST })
    public ModelAndView addAdhocRequest(@ModelAttribute("TrackingApp") final UserRegistration userRegistration, final ModelAndView mav) {
        this.addhocDao.addAdhoc(userRegistration);
        final List<UserRegistration> rosterdetails = (List<UserRegistration>)this.addhocDao.retrieveAllRosterDetails();
        mav.addObject("rosterdetails", (Object)rosterdetails);
        mav.setViewName("viewRosters");
        return mav;
    }
    
    @RequestMapping(value = { "/viewRoster" }, method = { RequestMethod.POST })
    public ModelAndView retrieveRosters(@ModelAttribute("TrackingApp") final UserRegistration userRegistration, final ModelAndView mav) {
        final List<UserRegistration> rosterdetails = (List<UserRegistration>)this.addhocDao.retrieveRosterDetailsByEmpId(userRegistration.getEmpId());
        if (rosterdetails.isEmpty()) {
            mav.addObject("message", (Object)"No Records found for given EmployeeId");
        }
        mav.addObject("rosterdetails", (Object)rosterdetails);
        mav.setViewName("viewRosters");
        return mav;
    }
    
    @RequestMapping(value = { "/viewAllRosters" }, method = { RequestMethod.GET })
    public ModelAndView retrieveAllRosters(final ModelAndView mav) {
        final List<UserRegistration> rosterdetails = (List<UserRegistration>)this.addhocDao.retrieveAllRosterDetails();
        mav.addObject("rosterdetails", (Object)rosterdetails);
        mav.setViewName("viewRosters");
        return mav;
    }
    
    @RequestMapping(value = { "/deleteRosters/{userid}" }, method = { RequestMethod.GET })
    public ModelAndView deleteRosters(@PathVariable final int userid, final ModelAndView mav) {
        this.addhocDao.deleteRoster(userid);
        final List<UserRegistration> rosterdetails = (List<UserRegistration>)this.addhocDao.retrieveAllRosterDetails();
        mav.addObject("rosterdetails", (Object)rosterdetails);
        mav.setViewName("viewRosters");
        mav.addObject("message", (Object)"The selected Roster has been deleted");
        return mav;
    }
    
    @RequestMapping(value = { "/retrieveRoster/{id}" }, method = { RequestMethod.GET })
    public ModelAndView retrieveRoster(final ModelAndView mav, UserRegistration userRegistration, @PathVariable final int id) {
        final List<UserRegistration> rosterdetails = (List<UserRegistration>)this.addhocDao.retrieveRosterDetails(id);
        if (rosterdetails != null) {
            userRegistration = rosterdetails.get(0);
        }
        mav.setViewName("editRosters");
        mav.addObject("rosterdetails", (Object)rosterdetails);
        mav.addObject("command", (Object)userRegistration);
        return mav;
    }
    
    @RequestMapping(value = { "/editRoster" }, method = { RequestMethod.POST })
    public ModelAndView editRoster(@ModelAttribute("TrackingApp") final UserRegistration userRegistration, final ModelAndView mav, final HttpServletRequest request) {
        this.addhocDao.editRoster(userRegistration);
        final String admin = (String)request.getSession().getAttribute("isAdmin");
        if (admin.equals("Y")) {
            final List<UserRegistration> rosterdetails = (List<UserRegistration>)this.addhocDao.retrieveAllRosterDetails();
            mav.addObject("rosterdetails", (Object)rosterdetails);
            mav.setViewName("viewRosters");
            mav.addObject("message", (Object)"The selected Roster has been Updated");
        }
        else {
            final List<UserRegistration> rosterdetails = (List<UserRegistration>)this.addhocDao.retrieveRosterDetailsByEmpId(userRegistration.getEmpId());
            mav.addObject("rosterdetails", (Object)rosterdetails);
            mav.setViewName("viewRosters");
            mav.addObject("message", (Object)"The selected Roster has been Updated");
        }
        return mav;
    }
    
    @RequestMapping(value = { "/exportRoster/{empId}" }, method = { RequestMethod.GET })
    public ModelAndView getExcel(final ModelAndView mav, @PathVariable final String empId) {
        final List<UserRegistration> rosterdetails = (List<UserRegistration>)this.addhocDao.retrieveRosterDetailsByEmpId(empId);
        mav.setViewName("EmployeeRostersListExcel");
        mav.addObject("employeeRosterList", (Object)rosterdetails);
        return mav;
    }
    
    @RequestMapping(value = { "/exportAllRosters" }, method = { RequestMethod.GET })
    public ModelAndView getAllExcel(final ModelAndView mav) {
        final List<UserRegistration> rosterdetails = (List<UserRegistration>)this.addhocDao.retrieveAllRosterDetails();
        mav.setViewName("EmployeeRostersListExcel");
        mav.addObject("employeeRosterList", (Object)rosterdetails);
        return mav;
    }
}