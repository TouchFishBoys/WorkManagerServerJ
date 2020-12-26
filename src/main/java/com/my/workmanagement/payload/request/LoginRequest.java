package com.my.workmanagement.payload.request;

import com.my.workmanagement.model.ERole;

import javax.validation.constraints.NotEmpty;

public class LoginRequest {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private ERole role;

    public void setUsername(String username) {
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
