package com.automated.learntest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Map;

@Service
public class useraccount {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /*用户名查询*/
    public String queryusername(Map<String,String> map){

        String sqlaccount =String.format("SELECT COUNT(*) FROM `user` WHERE NAME=\"%s\";",
                map.get("username"));
        return jdbcTemplate.queryForObject(sqlaccount,String.class);
    }

    /*用户名和密码查询 */
    public String queryuserpassword(Map<String,String> map){

        String sql =String.format("SELECT COUNT(*) FROM `user` WHERE NAME=\"%s\" AND PASSWORD=\"%s\";",
                map.get("username"),map.get("password"));
        return jdbcTemplate.queryForObject(sql,String.class);
    }

    /*修改密码 */
    public int modpassword(Map<String,String> map){

        String Modpassword =String.format("UPDATE `user` SET PASSWORD=\"%s\" WHERE NAME=\"%s\";",
                map.get("password"),map.get("username"));
        return jdbcTemplate.update(Modpassword);

    }

    /*查询用户数据 */
    public List<Map<String, Object>> selectuser(int n, String m){

        String select = String.format("SELECT * FROM `user` LIMIT %s , %s;", n,m);
        return jdbcTemplate.queryForList(select);

    }



}
