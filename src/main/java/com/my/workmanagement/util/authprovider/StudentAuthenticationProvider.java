package com.my.workmanagement.util.authprovider;

import com.my.workmanagement.model.token.StudentAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class StudentAuthenticationProvider extends DaoAuthenticationProvider {
    public StudentAuthenticationProvider(PasswordEncoder encoder, UserDetailsService service) {
        setPasswordEncoder(encoder);
        setUserDetailsService(service);
    }

    @Override
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        super.setPasswordEncoder(passwordEncoder);
    }

    @Override
    public void setUserDetailsPasswordService(UserDetailsPasswordService userDetailsPasswordService) {
        super.setUserDetailsPasswordService(userDetailsPasswordService);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return StudentAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
