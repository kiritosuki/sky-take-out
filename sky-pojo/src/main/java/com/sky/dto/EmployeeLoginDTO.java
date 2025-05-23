package com.sky.dto;


import java.io.Serializable;

public class EmployeeLoginDTO implements Serializable {

    private String username;

    private String password;

    @Override
    public String toString() {
        return "EmployeeLoginDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
