package com.my.workmanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "workmanager.auth")
public class WMAuthConfig {
    private String jwtSecret;
    private Integer jwtExpirationMs;

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public void setJwtExpirationMs(Integer jwtExpirationMs) {
        this.jwtExpirationMs = jwtExpirationMs;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public Integer getJwtExpirationMs() {
        return jwtExpirationMs;
    }
}
