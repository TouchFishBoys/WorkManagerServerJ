package com.my.workmanagement.payload.response;

public class JwtResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static final class JwtResponseBuilder {
        private String token;

        private JwtResponseBuilder() {
        }

        public static JwtResponseBuilder aJwtResponse() {
            return new JwtResponseBuilder();
        }

        public JwtResponseBuilder withToken(String token) {
            this.token = token;
            return this;
        }

        public JwtResponse build() {
            JwtResponse jwtResponse = new JwtResponse();
            jwtResponse.setToken(token);
            return jwtResponse;
        }
    }
}
