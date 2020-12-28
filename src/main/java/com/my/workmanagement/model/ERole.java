package com.my.workmanagement.model;

import org.springframework.security.core.GrantedAuthority;

public enum ERole implements GrantedAuthority {
    ROLE_TEACHER, ROLE_STUDENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
