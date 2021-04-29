package com.automated.learntest.controller;

import com.alibaba.fastjson.JSONObject;
import com.automated.learntest.Dao.ProjectDao;
import com.automated.learntest.data.Responseinfo;
import com.automated.learntest.data.Resultinfo;
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

@ResponseBody
@RestController
public class Project extends Responseinfo {

    private ProjectDao userService;

    @Autowired
    public void setUserService (ProjectDao userService) {
        this.userService = userService;
    }


    @RequestMapping("/query/projectdata")
    public Resultinfo GetProject(){

        Resultinfo result = new Resultinfo();

        List<Map<String, Object>> select;
        List<Object> listData = new ArrayList<>();


        select = userService.QueryProjectData();

        result.setCode(getSUCCESS_CODE());
        result.setCount(Integer.parseInt(userService.QueryProjectcount()));
        result.setMsg(getACCOUNT_SUCCESS());
        result.setErrormsg("");

        for (Map<String, Object> UserDatum : select) {
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(UserDatum);
            listData.add(jsonObject);
        }
        result.setData(listData);
        return result;
    }

    @RequestMapping("/add/projectinfo")
    public Resultinfo addproject(HttpServletRequest request, HttpSession session){

        Resultinfo result = new Resultinfo();

        Boolean resultproject = userService.QueryProject(request.getParameter("project"));
        if(!resultproject){
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getPROJECT_FOUNDED());
            return result;
        }

        Map<String,String> TeMap = new HashMap<>();

        TeMap.put("project",request.getParameter("project"));
        TeMap.put("serial",String.valueOf(Integer.parseInt(userService.QueryProjectcount())+1));
        TeMap.put("content",request.getParameter("content"));
        TeMap.put("creator", (String) session.getAttribute("username"));
        List<Map<String, Object>> select;
        List<Object> listData = new ArrayList<>();
        boolean resultprojectadd = userService.AddProject(TeMap);
        if(resultprojectadd){

            select = userService.QueryProjectData2(request.getParameter("project"));
            for (Map<String, Object> UserDatum : select) {
                JSONObject jsonObject = (JSONObject) JSONObject.toJSON(UserDatum);
                listData.add(jsonObject);
            }
            result.setData(listData);
            result.setCode(getSUCCESS_CODE());
            result.setMsg(getACCOUNT_SUCCESS());
            result.setErrormsg("");

        }else {
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getPROJECT_ERROR());
        }
        return result;

    }

}
