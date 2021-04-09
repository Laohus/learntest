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

    private UserDao userDao;

    @Autowired
    public void setUserDao (UserDao UserDao) {
        this.userDao = UserDao;
    }


    /*用户名查询*/
    public String QueryUser(String username){

        String Key ="SELECT COUNT(*) FROM `user` WHERE NAME=? ;";
        return jdbcTemplate.queryForObject(Key,String.class,username);
    }

    /*用户名和密码查询 */
    public String QueryUserPass(String username,String password){

        String Key ="SELECT COUNT(*) FROM `user` WHERE NAME=? AND PASSWORD=?;";
        return jdbcTemplate.queryForObject(Key,String.class,username,password);
    }

    /*修改密码 */
    public boolean ModUser(Map<String, String> UserData){

        String Key = "UPDATE `user` SET `password`=? ,`email`=? ,`lines`=? WHERE `name` = ?;";
        return jdbcTemplate.update(Key,UserData.get("password"),UserData.get("email"),UserData.get("line")
                ,UserData.get("username"))>0;

    }

    /*查询用户数据 */
    public List<Map<String, Object>> QueryUserData(int page, int limit){

        String Key = "SELECT * FROM `user` LIMIT ? , ?;";
        return jdbcTemplate.queryForList(Key,(page-1)*limit,page*limit);

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

        String Key = "INSERT into`user` (`name`,`password`,`CreationTime`,`email`,`age`,`sex`,`lines`,`jobs`) VALUES(?,?,?,?,?,?,?,?);";
        return jdbcTemplate.update(Key,UserData.get("username"),UserData.get("password"),formatter.format(date),
                UserData.get("email"),UserData.get("age"),UserData.get("sex"),UserData.get("line"),
                UserData.get("jobs"))>0;

    }


    /*删除用户数据 */
    public boolean DelUser(JSONArray User){

        for (Object name : User) {
            String Key = "DELETE FROM `user` WHERE `name`=? ;";
            if (jdbcTemplate.update(Key,name) == 0) {
                return false;
            }
        }
        return true;

    }


}
