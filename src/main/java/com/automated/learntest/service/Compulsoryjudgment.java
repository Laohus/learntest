package com.automated.learntest.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@Service
public class Compulsoryjudgment{

    public boolean checkparameter(HttpServletRequest request, List<String> Parlist){

        System.out.println("cesss");

        Enumeration enu=request.getParameterNames();

        while(enu.hasMoreElements()){
            System.out.println((String)enu.nextElement());
            boolean res = Parlist.contains((String)enu.nextElement());
            if (!res){
                return false;
            }
        }
        return true;


    }

}
