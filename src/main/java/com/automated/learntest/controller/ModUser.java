package com.automated.learntest.controller;

import com.automated.learntest.data.Responseinfo;
import com.automated.learntest.data.Resultinfo;
import com.automated.learntest.Dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
public class ModUser extends Responseinfo {

    private UserDao userService;

    @Autowired
    public void setUserService (UserDao userService) {
        this.userService = userService;
    }

    @RequestMapping("/login/account")
    public Resultinfo LoginAccount (HttpServletRequest request , HttpSession session){

        Resultinfo result = new Resultinfo();

        String UserName = request.getParameter("username");
        String PassWord = request.getParameter("password");
        String ResultUser = userService.QueryUser(UserName);

        if(ResultUser.equals("1")){
            String ResultModPassword = userService.QueryUserPass(UserName,PassWord);
            if(ResultModPassword.equals("1")){
                session.setAttribute("username",UserName);
                result.setCode(getSUCCESS_CODE());
                result.setMsg(getACCOUNT_SUCCESS());
            }else {
                result.setCode(getFAIL_CODE());
                result.setErrormsg(getACCOUNT_ERROR());
            }
        }else {
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getACCOUNT_NO_FOUND());

        }
        return result;

    }

    @RequestMapping("/Functional/Authority")
    public Resultinfo LoginAuthority (HttpSession session){

        Resultinfo result = new Resultinfo();

        String username = (String) session.getAttribute("username");

        Map<String,Boolean> AuthorityMap = new HashMap<>();

        boolean Userinfo = false;

        if(username.equals("admin")){
            Userinfo = true;

        }
        AuthorityMap.put("Userinfo",Userinfo);
        result.setCode(getSUCCESS_CODE());
        result.setMsg(getACCOUNT_SUCCESS());
        result.setData(AuthorityMap);
        return result;

    }

}
