package com.automated.learntest.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ProjectDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate (JdbcTemplate JdbcTemplate) {
        this.jdbcTemplate = JdbcTemplate;
    }

    /*添加项目 */
    public boolean AddProject(Map<String, String> UserData){

        String Key = "INSERT into`project` (`name`,`serial`,`content`,`creator`) VALUES(?,?,?,?);";
        return jdbcTemplate.update(Key,UserData.get("project"),UserData.get("serial"),
                UserData.get("content"),UserData.get("creator"))>0;

    }

    /*项目 */
    public List<Map<String, Object>> QueryProjectData(){

        String Key = "SELECT * FROM `project`;";
        return jdbcTemplate.queryForList(Key);

    }

    /*项目2 */
    public List<Map<String, Object>> QueryProjectData2(String name){

        String Key = "SELECT * FROM `project` where `name`=? ;";
        return jdbcTemplate.queryForList(Key,name);

    }

    /*项目名查询*/
    public Boolean QueryProject(String name){

        String Key ="SELECT count(*) FROM `project` WHERE NAME=? ;";
        return !Objects.equals(jdbcTemplate.queryForObject(Key, String.class, name), "1");
    }

    /*项目数查询*/
    public String QueryProjectcount(){

        String Key ="SELECT MAX(serial) FROM project";

        return jdbcTemplate.queryForObject(Key,String.class);
    }

}
