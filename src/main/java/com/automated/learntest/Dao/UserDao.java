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
    public List<Map<String, Object>> selectuser(int page, int limit){

        String select = String.format("SELECT * FROM `user` LIMIT %s , %s;",  (page-1)*limit,page*limit);
        return jdbcTemplate.queryForList(select);

    }

    /*查询用户数据总数 */
    public Integer selectuser(){

        String select = "SELECT count(*) from `user`";
        return jdbcTemplate.queryForObject(select,Integer.class);

    }

    /*添加用户数据 */
    public boolean adduser(Map<String, String> temap){

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String select = String.format("INSERT into`user` (`name`,`password`,`cratetime`,`email`,`age`,`sex`,`lines`,`jobs`) VALUES(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\");",
                temap.get("username"),getADD_PASSWORD(),formatter.format(date),temap.get("email"),temap.get("age"),temap.get("sex"),temap.get("line"),temap.get("position"));
        return jdbcTemplate.update(select)>0;

    }


    /*删除用户数据 */
    public boolean deluser(JSONArray listtmp){

        for (Object name : listtmp) {
            String select = String.format("DELETE FROM `user` WHERE `name`=\"%s\";", name);
            if (jdbcTemplate.update(select) == 0) {
                return false;
            }
        }
        return true;

    }


}
