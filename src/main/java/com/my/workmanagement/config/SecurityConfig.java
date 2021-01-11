package com.my.workmanagement.config;

import com.my.workmanagement.filter.CorsFilter;
import com.my.workmanagement.filter.JwtTokenFilter;
import com.my.workmanagement.util.authprovider.StudentAuthenticationProvider;
import com.my.workmanagement.util.authprovider.TeacherAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_WHITELIST = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/auth/login",
            "/v3/api-docs",
            "/error",
            "/actuator/**"
    };
    private final UserDetailsService teacherDetailsService;
    private final UserDetailsService studentDetailsService;
    @Resource
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    SecurityConfig(
            @Qualifier("teacherDetailsService") UserDetailsService teacherDetailsService,
            @Qualifier("studentDetailsService") UserDetailsService studentDetailsService
    ) {
        this.studentDetailsService = studentDetailsService;
        this.teacherDetailsService = teacherDetailsService;
    }

    @Bean("TeacherAuthenticationProvider")
    DaoAuthenticationProvider daoTeacherAuthentication() {
        return new TeacherAuthenticationProvider(encoder(), teacherDetailsService);
    }

    @Bean("StudentAuthenticationProvider")
    DaoAuthenticationProvider daoStudentAuthentication() {
        return new StudentAuthenticationProvider(encoder(), studentDetailsService);
    }

    @Bean
    public PasswordEncoder encoder() {
        // return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // 使用Jwt所以选择无状态
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // OPTIONS 请求全部放行
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 登录接口放行
                .antMatchers(AUTH_WHITELIST).permitAll()
                // 其它接口进行验证
                .anyRequest().authenticated();
        // 添加 Filter
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(corsFilter(), SessionManagementFilter.class);
        http.headers().cacheControl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(AUTH_WHITELIST);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() {
        DaoAuthenticationProvider dapTeacher = new DaoAuthenticationProvider();
        dapTeacher.setUserDetailsService(teacherDetailsService);

        DaoAuthenticationProvider dapStudent = new DaoAuthenticationProvider();
        dapStudent.setUserDetailsService(studentDetailsService);

        return new ProviderManager(dapTeacher, dapStudent);
    }
}
