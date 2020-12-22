package com.my.workmanagement.payload.request;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.my.workmanagement.model.User;

import java.util.Date;

public class LoginRequest {
    public static String getToken(User user) {
        Date date = new Date(System.currentTimeMillis());
        String token = "";
        token = JWT.create()
                .withAudience(user.getUsername())
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
