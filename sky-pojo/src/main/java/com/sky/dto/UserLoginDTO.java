package com.sky.dto;


import java.io.Serializable;

/**
 * C端用户登录
 */
public class UserLoginDTO implements Serializable {

    private String code;

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "code='" + code + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
