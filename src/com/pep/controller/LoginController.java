package com.pep.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pep.pojo.UserRegistration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import com.pep.dao.UserDao;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.stereotype.Controller;

@Controller
@SessionAttributes({ "isAdmin", "isSessionAlive", "pMail", "tcsEmpId" })
public class LoginController
{
    @Autowired
    private UserDao userDao;
    
    @RequestMapping({ "/logIn" })
    public ModelAndView loginUser() {
        return new ModelAndView("loginPage", "command", (Object)new UserRegistration());
    }
    
    @RequestMapping(value = { "/loginAction" }, method = { RequestMethod.POST })
    public ModelAndView loginUser(@ModelAttribute("TrackingApp") final UserRegistration userRegistration, final ModelMap model, ModelAndView mav) {
        model.addAttribute("command", (Object)new UserRegistration());
        final UserRegistration user = this.userDao.loginUser(userRegistration);
        if (null != user) {
            mav = new ModelAndView("loginSucces");
            mav.addObject("pMail", (Object)user.getPepsicoMail().replace(".contractor@pepsico.com", ""));
            mav.addObject("tcsEmpId", (Object)user.getEmpId());
            mav.addObject("isAdmin", (Object)user.getAdmin());
            mav.addObject("isSessionAlive", (Object)true);
        }
        else {
            mav = new ModelAndView("loginPage");
            mav.addObject("message", (Object)"Login Failed.. Please try Again.. ");
            mav.addObject("command", (Object)new UserRegistration());
        }
        return mav;
    }
    
    @RequestMapping(value = { "/changePassword" }, method = { RequestMethod.POST })
    public ModelAndView changePassword(@ModelAttribute("TrackingApp") final UserRegistration userRegistration, final ModelAndView mav, final HttpServletRequest req) {
        userRegistration.setEmpId((String)req.getSession().getAttribute("tcsEmpId"));
        final UserRegistration user = this.userDao.loginUser(userRegistration);
        mav.addObject("command", (Object)new UserRegistration());
        if (null != user) {
            user.setNewPwd(userRegistration.getNewPwd());
            this.userDao.changePwd(user);
            mav.setViewName("loginSucces");
            mav.addObject("message", (Object)"Password successfully Updated.. ");
        }
        else {
            mav.setViewName("loginSucces");
            mav.addObject("message", (Object)"Please enter correct currentPassword.. ");
        }
        return mav;
    }
    
    @RequestMapping({ "/forgotPwd" })
    public String forgotPwd() {
        return "forgotPwd";
    }
    
    @RequestMapping(value = { "/retrievePwd" }, method = { RequestMethod.POST })
    public ModelAndView retrievePwd(@ModelAttribute("TrackingApp") final UserRegistration userRegistration, final ModelMap model, final ModelAndView mav) {
        final UserRegistration user = this.userDao.forgotPwd(userRegistration);
        if (null != user) {
            mav.addObject("tcsEmpId", (Object)user.getEmpId());
            mav.setViewName("newPwd");
        }
        else {
            mav.addObject("message", (Object)"Please enter correct currentPassword.. ");
            mav.setViewName("forgotPwd");
        }
        return mav;
    }
    
    @RequestMapping(value = { "/setNewPassword" }, method = { RequestMethod.POST })
    public ModelAndView setNewPassword(@ModelAttribute("TrackingApp") final UserRegistration userRegistration, final ModelAndView mav, final HttpServletRequest req) {
        userRegistration.setEmpId((String)req.getSession().getAttribute("tcsEmpId"));
        this.userDao.changePwd(userRegistration);
        mav.addObject("message", (Object)"Password successfully Updated.. Please login with new Password ");
        mav.setViewName("loginPage");
        return mav;
    }
}