package com.my.workmanagement.util.authprovider;

import com.my.workmanagement.model.token.TeacherAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TeacherAuthenticationProvider extends DaoAuthenticationProvider {

    public TeacherAuthenticationProvider(PasswordEncoder encoder, UserDetailsService service) {
        setPasswordEncoder(encoder);
        setUserDetailsService(service);
    }

    @Override
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        super.setPasswordEncoder(passwordEncoder);
    }

    @Override
    public void setUserDetailsPasswordService(UserDetailsPasswordService userDetailsService) {
        super.setUserDetailsPasswordService(userDetailsService);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // return super.supports(authentication);
        return TeacherAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
