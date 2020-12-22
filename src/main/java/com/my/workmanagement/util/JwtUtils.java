package com.my.workmanagement.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.my.workmanagement.model.User;

import java.util.Date;

public class JwtUtils {
    public static final String JWT_CLAIM_USERNAME = "username";
    public static final String JWT_CLAIM_ROLE = "role";
    public static final String JWT_AUTH_ISSURER = "issurer";


    public static String generateToken(User user) {
        Date date = new Date(System.currentTimeMillis());
        return JWT.create()
                .withIssuer(JWT_AUTH_ISSURER)
                .withExpiresAt(date)
                .withClaim(JWT_CLAIM_USERNAME, user.getUsername())
                .withClaim("role", "")
                .sign(Algorithm.HMAC256(user.getPassword())); // 加密确保不会被篡改
    }

    public static boolean validateToken(String token, String username, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(JWT_AUTH_ISSURER)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim(JWT_CLAIM_USERNAME).asString().equals(JWT_CLAIM_USERNAME);
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            return false;
        }
    }

    public static String getUsername(String token) {

    }

    public static boolean isExpired(String token) {

    }
}
