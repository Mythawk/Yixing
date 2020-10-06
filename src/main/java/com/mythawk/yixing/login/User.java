package com.mythawk.yixing.login;

public class User {
    private Integer id ;
    private String loginName;
    private String pwd;


    public User() {
    }

    public User(Integer id, String loginName, String pwd) {
        this.id = id;
        this.loginName = loginName;
        this.pwd = pwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
