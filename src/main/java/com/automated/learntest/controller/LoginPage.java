package com.automated.learntest.controller;


import com.automated.learntest.data.Responseinfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;


@Controller
public class LoginPage extends Responseinfo {

    @RequestMapping("/login")
    public String login(){ return "login"; }

    @RequestMapping("/home")
    public String home(){ return "home"; }

    @RequestMapping("/LoginOut")
    public String LoginOut(HttpSession session, SessionStatus sessionStatus){
        session.invalidate();
        sessionStatus.setComplete();
        return "redirect:login";
    }

}
