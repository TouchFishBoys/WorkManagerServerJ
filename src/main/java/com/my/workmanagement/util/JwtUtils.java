package com.my.workmanagement.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.my.workmanagement.config.WMAuthConfig;
import com.my.workmanagement.model.ERole;
import com.my.workmanagement.model.WMUserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

/**
 * Jwt 工具类
 */
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

    /**
     * 生成 JwtToken
     *
     * @param WMUserDetails 用户信息
     * @return 生成的 Token
     */
    public static String generateToken(WMUserDetails WMUserDetails) {
        Date date = new Date(System.currentTimeMillis() + wmAuthConfig.getJwtExpirationMs());
        return JWT.create()
                .withIssuer(JWT_AUTH_ISSURER)
                .withExpiresAt(date)
                // 保存 Username
                .withClaim(JWT_CLAIM_USERNAME, WMUserDetails.getUsername())
                // 保存 Role
                .withClaim(JWT_CLAIM_ROLE, WMUserDetails.getRole().name())
                // 加密 Sign
                .sign(algorithm);
    }

    /**
     * 验证 Token 是否有效
     *
     * @param token JwtToken
     * @return 是否有效
     */
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

    // 仅获取不进行验证
    public static String getUsername(String token) {
        return JWT.decode(token).getClaim(JWT_CLAIM_USERNAME).asString();
    }

    // 仅获取不进行验证
    public static ERole getRole(String token) {
        String role = JWT.decode(token).getClaim(JWT_CLAIM_ROLE).asString();
        return ERole.valueOf(role);
    }
}
