package com.my.workmanagement.model;

import org.springframework.security.core.GrantedAuthority;

public enum ERole implements GrantedAuthority {
    TEACHER, STUDENT;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
