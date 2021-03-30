package com.automated.learntest.controller;

import com.alibaba.fastjson.JSONArray;
import com.automated.learntest.data.Responseinfo;
import com.automated.learntest.data.Resultinfo;
import com.automated.learntest.service.useraccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
public class Userdata extends Responseinfo {

    @Autowired
    private useraccount userService;


    @RequestMapping("/query/userdata")
    public Resultinfo queryuser(HttpServletRequest request){

        Resultinfo result = new Resultinfo();

        String page = request.getParameter("page");
        int pages = Integer.parseInt(page);
        String limit = request.getParameter("limit");
        int limits = Integer.parseInt(limit);

        List<Map<String, Object>> select;
        List<Object> listdata = new ArrayList<>();


        select = userService.selectuser(pages,limits);
        Integer count = userService.selectuser();

        result.setCode(getSUCCESS_CODE());
        result.setCount(count);
        result.setMsg(getACCOUNT_SUCCESS());
        result.setErrormsg("");

        for (Map<String, Object> userdatum : select) {
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(userdatum);
            listdata.add(jsonObject);
        }
        result.setData(listdata);
        return result;
    }

    @RequestMapping("/add/userinfo")
    public Resultinfo adduser(HttpServletRequest request){

        Resultinfo result = new Resultinfo();
        Map<String,String> tmmap = new HashMap<>();
        tmmap.put("username",request.getParameter("username"));
        tmmap.put("email",request.getParameter("email"));
        tmmap.put("age",request.getParameter("age"));
        tmmap.put("sex",request.getParameter("sex"));
        tmmap.put("position",request.getParameter("position"));
        tmmap.put("line",request.getParameter("line"));

        String name = userService.queryusername(tmmap);

        if(name.equals("0")){
            Boolean res = userService.adduser(tmmap);
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
    public Resultinfo deluser(@RequestBody JSONObject data){

        Resultinfo result = new Resultinfo();
        JSONArray listtmp;
        listtmp = data.getJSONArray("name");

        boolean res = userService.deluser(listtmp);
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
