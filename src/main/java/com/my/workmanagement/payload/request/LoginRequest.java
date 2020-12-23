package com.my.workmanagement.payload.request;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.my.workmanagement.model.ERole;
import com.my.workmanagement.model.User;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class LoginRequest {
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
    @NotEmpty
    private ERole role;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
