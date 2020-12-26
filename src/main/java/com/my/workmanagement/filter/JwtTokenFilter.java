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
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
    @Autowired
    @Qualifier("studentDetailsService")
    private UserDetailsService studentDetailsService;
    @Autowired
    @Qualifier("teacherDetailsService")
    private UserDetailsService teacherDetailsService;
    @Autowired
    private JwtUtils jwtUtils;

    public static final String HEADER_AUTH = "Authorization";
    public static final String TOKEN_HEAD = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HEADER_AUTH);
        logger.debug("Receive token: {}", token);

        if (null != token && token.startsWith(TOKEN_HEAD)) {
            token = token.substring(TOKEN_HEAD.length());
            // 从 Token 中获取用户名（工号或学号）
            String username = JwtUtils.getUsername(token);
            logger.debug("Username: {}", username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                logger.debug("Start authentication");
                UserDetails userDetails = null;
                if (JwtUtils.validateToken(token)) {
                    logger.debug(JwtUtils.getRole(token).name());
                    switch (JwtUtils.getRole(token)) {
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
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
