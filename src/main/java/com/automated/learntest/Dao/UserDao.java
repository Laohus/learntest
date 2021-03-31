package com.automated.learntest.Dao;

import com.alibaba.fastjson.JSONArray;
import com.automated.learntest.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserDao extends User {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate (JdbcTemplate JdbcTemplate) {
        this.jdbcTemplate = JdbcTemplate;
    }


    /*用户名查询*/
    public String QueryUser(String username){

        String Key =String.format("SELECT COUNT(*) FROM `user` WHERE NAME=\"%s\";",
                username);
        return jdbcTemplate.queryForObject(Key,String.class);
    }

    /*用户名和密码查询 */
    public String QueryUserPass(String username,String password){

        String Key =String.format("SELECT COUNT(*) FROM `user` WHERE NAME=\"%s\" AND PASSWORD=\"%s\";",
                username,password);
        return jdbcTemplate.queryForObject(Key,String.class);
    }

    /*修改密码 */
    public int ModUser(String username,String NewPassword){

        String Key =String.format("UPDATE `user` SET PASSWORD=\"%s\" WHERE NAME=\"%s\";",
                NewPassword,username);
        return jdbcTemplate.update(Key);

    }

    /*查询用户数据 */
    public List<Map<String, Object>> QueryUserData(int page, int limit){

        String Key = String.format("SELECT * FROM `user` LIMIT %s , %s;",  (page-1)*limit,page*limit);
        return jdbcTemplate.queryForList(Key);

    }

    /*查询用户数据总数 */
    public Integer UserCount(){

        String Key = "SELECT count(*) from `user`";
        return jdbcTemplate.queryForObject(Key,Integer.class);

    }

    /*添加用户数据 */
    public boolean adduser(Map<String, String> UserData){

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String Key = String.format("INSERT into`user` (`name`,`password`,`CreationTime`,`email`,`age`,`sex`,`lines`,`jobs`) VALUES(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\");",
                UserData.get("username"),getADD_PASSWORD(),formatter.format(date),UserData.get("email"),UserData.get("age"),UserData.get("sex"),UserData.get("line"),UserData.get("position"));
        return jdbcTemplate.update(Key)>0;

    }


    /*删除用户数据 */
    public boolean DelUser(JSONArray User){

        for (Object name : User) {
            String Key = String.format("DELETE FROM `user` WHERE `name`=\"%s\";", name);
            if (jdbcTemplate.update(Key) == 0) {
                return false;
            }
        }
        return true;

    }


}
