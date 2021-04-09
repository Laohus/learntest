package com.automated.learntest.controller;

import com.alibaba.fastjson.JSONArray;
import com.automated.learntest.data.Responseinfo;
import com.automated.learntest.data.Resultinfo;
import com.automated.learntest.Dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@ResponseBody
@RestController
public class Userdata extends Responseinfo {

    private UserDao userService;

    @Autowired
    public void setUserService (UserDao userService) {
        this.userService = userService;
    }


    @RequestMapping("/query/userdata")
    public Resultinfo GetUser(HttpServletRequest request){

        Resultinfo result = new Resultinfo();

        String page = request.getParameter("page");
        int pages = Integer.parseInt(page);
        String limit = request.getParameter("limit");
        int limits = Integer.parseInt(limit);

        List<Map<String, Object>> select;
        List<Object> listData = new ArrayList<>();


        select = userService.QueryUserData(pages,limits);
        Integer count = userService.UserCount();

        result.setCode(getSUCCESS_CODE());
        result.setCount(count);
        result.setMsg(getACCOUNT_SUCCESS());
        result.setErrormsg("");

        for (Map<String, Object> UserDatum : select) {
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(UserDatum);
            listData.add(jsonObject);
        }
        result.setData(listData);
        return result;
    }

    @RequestMapping("/add/userinfo")
    public Resultinfo adduser(HttpServletRequest request, HttpSession session){

        Resultinfo result = new Resultinfo();
        Map<String,String> TeMap = new HashMap<>();

        String sessionUsername = (String) session.getAttribute("username");
        if (!sessionUsername.equals("admin")){
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getNOT_ADMIN());
            return result;
        }


        TeMap.put("username",request.getParameter("username"));
        TeMap.put("password",request.getParameter("password"));
        TeMap.put("email",request.getParameter("email"));
        TeMap.put("age",request.getParameter("age"));
        TeMap.put("sex",request.getParameter("sex"));
        TeMap.put("jobs",request.getParameter("jobs"));
        TeMap.put("line",request.getParameter("line"));

        String name = userService.QueryUser(request.getParameter("username"));

        if(name.equals("0")){
            Boolean res = userService.adduser(TeMap);
            if(res.equals(true)){
                result.setCode(getSUCCESS_CODE());
                result.setMsg(getACCOUNT_SUCCESS());
                result.setErrormsg("");
            }else {
                result.setCode(getFAIL_CODE());
                result.setErrormsg(getACCOUNT_ERROR());
            }

        }else {
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getACCOUNT__FOUNDED());
        }
        return result;

    }


    @RequestMapping("/mod/userinfo")
    public Resultinfo moduser(@RequestBody JSONObject User, HttpSession session){

        Resultinfo result = new Resultinfo();
        String sessionUsername = (String) session.getAttribute("username");
        if (!sessionUsername.equals("admin")){
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getNOT_ADMIN());
            return result;
        }

        Map<String,String> TeMap = new HashMap<>();
        TeMap.put("username", User.getString("name"));
        TeMap.put("password",User.getString("password"));
        TeMap.put("email",User.getString("email"));
        TeMap.put("line",User.getString("line"));


        String ResultMod = userService.QueryUserPass(User.getString("name"),
                User.getString("password"));
        if(ResultMod.equals("1")){
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getEDITACCOUNT_NEWOLD_SAME());
            return result;
        }

        String count = userService.QueryUser(User.getString("name"));
        if(count.equals("1")){
            Boolean res = userService.ModUser(TeMap);
            if(res.equals(true)){
                result.setCode(getSUCCESS_CODE());
                result.setMsg(getACCOUNT_SUCCESS());
                result.setErrormsg("");
            }else {
                result.setCode(getFAIL_CODE());
                result.setErrormsg(getACCOUNT_ERROR());
            }

        }else {
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getACCOUNT__FOUNDED());
        }
        return result;

    }

    @RequestMapping("/del/userinfo")
    public Resultinfo DelUser(@RequestBody JSONObject data, HttpSession session){

        Resultinfo result = new Resultinfo();

        String sessionUsername = (String) session.getAttribute("username");
        if (!sessionUsername.equals("admin")){
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getNOT_ADMIN());
            return result;
        }

        JSONArray User;
        User = data.getJSONArray("name");

        boolean res = userService.DelUser(User);
        if(res){
            result.setCode(getSUCCESS_CODE());
            result.setMsg(getACCOUNT_SUCCESS());
            result.setErrormsg("");
            return result;
        }
        result.setCode(getFAIL_CODE());
        result.setErrormsg(getACCOUNT_DELERROR());
        return result;


    }

}
