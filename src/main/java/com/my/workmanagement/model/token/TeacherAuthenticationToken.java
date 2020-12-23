package com.my.workmanagement.model.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class TeacherAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public TeacherAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
