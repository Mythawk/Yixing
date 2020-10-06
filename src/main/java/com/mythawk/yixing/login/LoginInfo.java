package com.mythawk.yixing.login;


public class LoginInfo {
    private Integer id;
    private String loginName;
    private String password;
    private String verity;

    public LoginInfo() {
    }

    public LoginInfo(Integer id, String loginName, String password, String verity) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.verity = verity;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerity() {
        return verity;
    }

    public void setVerity(String verity) {
        this.verity = verity;
    }
}
