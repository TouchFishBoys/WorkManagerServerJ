package com.my.workmanagement.model.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class StudentAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public StudentAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
