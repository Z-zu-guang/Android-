package com.sise.comprehension.pojo;

import java.io.Serializable;




public class LoginInfo implements Serializable {
    private String no;
    private String password;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginInfo(String no, String password) {
        this.no = no;
        this.password = password;
    }

    public LoginInfo() {
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "no='" + no + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
