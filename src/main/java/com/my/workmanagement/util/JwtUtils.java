package com.my.workmanagement.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.my.workmanagement.config.WMAuthConfig;
import com.my.workmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

@Component
public class JwtUtils {
    @Resource
    private WMAuthConfig wmAuthConfigAutowired;

    private static WMAuthConfig wmAuthConfig;
    private static Algorithm algorithm;

    public static final String JWT_CLAIM_USERNAME = "username";
    public static final String JWT_CLAIM_ROLE = "role";
    public static final String JWT_AUTH_ISSURER = "issurer";

    @PostConstruct
    public void init() {
        wmAuthConfig = this.wmAuthConfigAutowired;
        algorithm = Algorithm.HMAC256(wmAuthConfig.getJwtSecret());
    }

    public static String generateToken(User user) {
        Date date = new Date(System.currentTimeMillis() + wmAuthConfig.getJwtExpirationMs());
        return JWT.create()
                .withIssuer(JWT_AUTH_ISSURER)
                .withExpiresAt(date)
                .withClaim(JWT_CLAIM_USERNAME, user.getUsername())
                .withClaim(JWT_CLAIM_ROLE, "")
                .sign(algorithm); // 加密确保不会被篡改
    }

    public static boolean validateToken(String token) {
        try {
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
        return JWT.decode(token).getClaim(JWT_CLAIM_USERNAME).asString();
    }
}
