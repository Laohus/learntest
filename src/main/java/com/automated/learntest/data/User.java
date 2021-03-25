package com.automated.learntest.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class User {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String cratetime;

    @Column
    private String email;

    @Column
    private String age;

    @Column
    private String sex;


    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) { this.password = password; }

    public String getCratetime() {
        return cratetime;
    }
    public void setCratetime(String cratetime) { this.cratetime = cratetime; }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) { this.email = email; }

    public String getAge() {
        return age;
    }
    public void setAge(String age) { this.age = age; }

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) { this.sex = sex; }

}
