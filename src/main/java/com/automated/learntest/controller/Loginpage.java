package com.automated.learntest.controller;


import com.automated.learntest.data.Responseinfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
public class Loginpage extends Responseinfo {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }



    @RequestMapping("/home")
    public String home(HttpServletRequest request) throws IOException {

        if(request.getHeader("Cookie")==null){
            return "redirect:login";
        }

        int count=request.getHeader("Cookie").indexOf("SESSION=");
        String Cookiebase=request.getHeader("Cookie").substring(8+count);
        byte[] bytes = new BASE64Decoder().decodeBuffer(Cookiebase);
        String Cookie = new String(bytes, StandardCharsets.UTF_8);

        HttpSession session = request.getSession();

        if(Cookie.equals(session.getId())){
            return "home";
        }else {
            return "redirect:login";
        }

    }

    @RequestMapping("/loginout")
    public String loginout(HttpServletRequest request, SessionStatus sessionStatu) throws IOException {


        if(request.getHeader("Cookie")==null){
            return "redirect:login";
        }
        int count=request.getHeader("Cookie").indexOf("SESSION=");
        String Cookiebase=request.getHeader("Cookie").substring(8+count);
        byte[] bytes = new BASE64Decoder().decodeBuffer(Cookiebase);
        String Cookie = new String(bytes, StandardCharsets.UTF_8);
        HttpSession session = request.getSession();
        if(Cookie.equals(session.getId())){
            session.invalidate();
            sessionStatu.setComplete();
        }
        return "redirect:login";


    }


}
