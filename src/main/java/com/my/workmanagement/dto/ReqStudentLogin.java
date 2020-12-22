package com.my.workmanagement.dto;

import javax.validation.constraints.NotEmpty;

public class ReqStudentLogin {
    @NotEmpty(message = "Username cant be empty")
    private String username;
    @NotEmpty(message = "Password cant be empty")
    private String password;

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

    @Override
    public String toString() {
        return "ReqStudentLogin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
