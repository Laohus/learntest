package com.automated.learntest.controller;

import com.automated.learntest.data.Responseinfo;
import com.automated.learntest.data.Resultinfo;
import com.automated.learntest.service.useraccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;


import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class Userdata extends Responseinfo {

    @Autowired
    private useraccount userService;


    @RequestMapping("/query/userdata")
    public Resultinfo queryuser(HttpServletRequest request){

        Resultinfo result = new Resultinfo();

        String page = request.getParameter("page");
        int n  =  (Integer.parseInt(page) - 1);
        String limit = request.getParameter("limit");

        List<Map<String, Object>> select;
        List<Object> listdata = new ArrayList<>();


        select = userService.selectuser(n,limit);

        result.setCode(getSUCCESS_CODE());
        result.setMsg(getACCOUNT_SUCCESS());
        result.setErrormsg("");

        for (Map<String, Object> userdatum : select) {
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(userdatum);
            listdata.add(jsonObject);
        }
        result.setData(listdata);
        return result;


    }

}
