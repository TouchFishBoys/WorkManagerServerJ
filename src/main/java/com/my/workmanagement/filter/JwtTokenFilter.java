package com.my.workmanagement.filter;

import com.my.workmanagement.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Jwt Token 过滤器
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    public static final String HEADER_AUTH = "Authorization";
    public static final String TOKEN_HEAD = "Bearer ";
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
    private final UserDetailsService studentDetailsService;
    private final UserDetailsService teacherDetailsService;

    @Autowired
    public JwtTokenFilter(
            @Qualifier("studentDetailsService") UserDetailsService studentDetailsService,
            @Qualifier("teacherDetailsService") UserDetailsService teacherDetailsService
    ) {
        this.teacherDetailsService = teacherDetailsService;
        this.studentDetailsService = studentDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader(HEADER_AUTH);

        if (null != token && token.startsWith(TOKEN_HEAD)) {
            logger.info("开始验证");
            logger.debug("Received token: {}", token);
            token = token.substring(TOKEN_HEAD.length());
            // 从 Token 中获取用户名（工号或学号）
            String username = JwtUtils.getUsername(token);
            logger.debug("Username: {}", username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = null;
                if (JwtUtils.validateToken(token)) { //Token 有效
                    logger.debug("传递的用户身份: {}", JwtUtils.getRole(token).name());
                    switch (JwtUtils.getRole(token)) {//获取 JWT 中的身份
                        case ROLE_STUDENT:
                            // 学生
                            userDetails = studentDetailsService.loadUserByUsername(JwtUtils.getUsername(token));
                            break;
                        case ROLE_TEACHER:
                            // 教师
                            userDetails = teacherDetailsService.loadUserByUsername(JwtUtils.getUsername(token));
                            break;
                    }
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    logger.info("无效的Token：{}", token);
                }
            }
            logger.info("结束验证");
        }
        filterChain.doFilter(request, response);
    }
}
