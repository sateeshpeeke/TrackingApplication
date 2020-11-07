package com.pep.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

@Controller
public class logoutController
{
    @RequestMapping({ "/logOut" })
    public String logoutUser(final HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:welcome";
    }
    
    @RequestMapping({ "/welcome" })
    public String loginUser() {
        return "index";
    }
}