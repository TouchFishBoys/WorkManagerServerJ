package com.my.workmanagement.payload.response;

import com.my.workmanagement.model.ERole;
import com.my.workmanagement.payload.PackedResponse;

import java.io.Serializable;

public class WhoAmIResponse implements Serializable {
    private String username;
    private ERole role;

    public WhoAmIResponse(String username, ERole role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }
}
