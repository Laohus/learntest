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

//    @RequestMapping("/Home/ModUser")
//    public Resultinfo HomeModUser(HttpServletRequest request, HttpSession session){
//
//        Resultinfo result = new Resultinfo();
//
//        String FirstPassword = request.getParameter("FirstPassword");
//        String SecondPassword = request.getParameter("SecondPassword");
//        if(!FirstPassword.equals(SecondPassword)){
//            result.setCode(getFAIL_CODE());
//            result.setErrormsg(getEDITACCOUNT_NEWOLD_NOTSAME());
//            return result;
//        }
//
//        String UserName = (String) session.getAttribute("username");
//        String ResultMod = userService.QueryUserPass(UserName,FirstPassword);
//
//        if(!ResultMod.equals("1")){
//            int res = userService.ModUser(UserName,FirstPassword);
//            if(res==1){
//                result.setCode(getSUCCESS_CODE());
//                result.setMsg(getACCOUNT_SUCCESS());
//            }else {
//                result.setCode(getFAIL_CODE());
//                result.setErrormsg(getACCOUNT_ERROR());
//            }
//
//        }else {
//            result.setCode(getFAIL_CODE());
//            result.setErrormsg(getEDITACCOUNT_NEWOLD_SAME());
//        }
//        return result;
//
//    }
}
