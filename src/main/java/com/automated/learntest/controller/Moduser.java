package com.automated.learntest.controller;

import com.automated.learntest.data.Responseinfo;
import com.automated.learntest.data.Resultinfo;
import com.automated.learntest.service.useraccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
public class Moduser extends Responseinfo {

    @Autowired
    private useraccount userService;

    @RequestMapping("/login/account")
    public Resultinfo loginhome (HttpServletRequest request){

        Resultinfo result = new Resultinfo();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Map<String, String> umap = new HashMap<String,String>();
        umap.put("username",username);
        umap.put("password",password);

        String resusername = userService.queryusername(umap);

        if(resusername.equals("1")){
            String respassword = userService.queryuserpassword(umap);
            if(respassword.equals("1")){
                HttpSession session = request.getSession();
                session.setAttribute(username,umap);
                result.setCode(getSUCCESS_CODE());
                result.setMsg(getACCOUNT_SUCCESS());
                return result;
            }else {
                result.setCode(getFAIL_CODE());
                result.setErrormsg(getACCOUNT_ERROR());
                return result;
            }
        }else {
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getACCOUNT_NO_FOUND());
            return result;

        }

    }

    @RequestMapping("/home/edituser")
    public Resultinfo modpassword(HttpServletRequest request) throws IOException {

        Resultinfo result = new Resultinfo();
        HttpSession session = request.getSession();
        String username = null;

        if(request.getHeader("Cookie")==null){
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getSESSION_TIMEOUT());
            return result;
        }

//        int count=request.getHeader("Cookie").indexOf("SESSION=");
//        String Cookiebase=request.getHeader("Cookie").substring(8+count);
//        byte[] bytes = new BASE64Decoder().decodeBuffer(Cookiebase);
//        String Cookie = new String(bytes, StandardCharsets.UTF_8);
//        if(!Cookie.equals(session.getId())){
//            result.setCode(getFAIL_CODE());
//            result.setErrormsg(getSESSION_TIMEOUT());
//            return result;
//        }

        String firstpassword = request.getParameter("firstpassword");
        String secendpassword = request.getParameter("secendpassword");
        if(!firstpassword.equals(secendpassword)){
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getEDITACCOUNT_NEWOLD_NOTSAME());
            return result;
        }

        Map<String, String> umap = new HashMap<String,String>();


        Enumeration<String> attrs = session.getAttributeNames();
        while(attrs.hasMoreElements()){
            username = attrs.nextElement().toString();
        }
        Map tmpMap =(Map) session.getAttribute(username);
        umap.put("password",firstpassword);
        umap.put("username", (String) tmpMap.get("username"));
        String newpassword = userService.queryuserpassword(umap);

        if(!newpassword.equals("1")){
            int res = userService.modpassword(umap);
            if(res==1){
                result.setCode(getSUCCESS_CODE());
                result.setMsg(getACCOUNT_SUCCESS());
                return result;
            }else {
                result.setCode(getFAIL_CODE());
                result.setErrormsg(getACCOUNT_ERROR());
                return result;
            }

        }else {
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getEDITACCOUNT_NEWOLD_SAME());
            return result;
        }

    }
}
