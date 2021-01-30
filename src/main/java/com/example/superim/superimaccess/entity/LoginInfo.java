package com.example.superim.superimaccess.entity;

import java.io.Serializable;

/**
 * @Description: 登陆信息类，落库Redis
 * @author: i_laowei
 * @date: 2021/1/19  15:56
 */

public class LoginInfo implements Serializable {

    private String userName;


    public LoginInfo() {
    }

    public LoginInfo(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
