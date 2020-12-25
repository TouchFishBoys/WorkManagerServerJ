package com.my.workmanagement.config;

import com.my.workmanagement.filter.JwtTokenFilter;
import com.my.workmanagement.util.authprovider.StudentAuthenticationProvider;
import com.my.workmanagement.util.authprovider.TeacherAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService teacherDetailsService;
    private final UserDetailsService studentDetailsService;

    @Autowired
    SecurityConfig(
            @Qualifier("teacherDetailsService") UserDetailsService teacherDetailsService,
            @Qualifier("studentDetailsService") UserDetailsService studentDetailsService
    ) {
        this.studentDetailsService = studentDetailsService;
        this.teacherDetailsService = teacherDetailsService;
    }

    /**
     * 注入 教师验证 Provider
     *
     * @return
     */
    @Bean("TeacherAuthenticationProvider")
    DaoAuthenticationProvider daoTeacherAuthentication() {
        return new TeacherAuthenticationProvider(encoder(), teacherDetailsService);
    }

    /**
     * 注入 学生验证 Provider
     *
     * @return
     */
    @Bean("StudentAuthenticationProvider")
    DaoAuthenticationProvider daoStudentAuthentication() {
        return new StudentAuthenticationProvider(encoder(), studentDetailsService);
    }

    /**
     * 注入 encoder
     *
     * @return
     */
    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoStudentAuthentication());
        auth.authenticationProvider(daoTeacherAuthentication());
    }

    @Bean
    public JwtTokenFilter authenticationTokenFilterBean() {
        return new JwtTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.csrf().disable()
                // 使用Jwt所以选择无状态
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // OPTIONS 请求全部放行
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 登录接口放行
                .antMatchers("/auth/login").permitAll()
                // 其它接口进行验证
                .anyRequest().authenticated();
        // 添加 Filter
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
