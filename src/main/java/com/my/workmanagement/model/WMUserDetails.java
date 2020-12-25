package com.my.workmanagement.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class WMUserDetails implements UserDetails {
    private final String username;
    private final String password;
    private final ERole role;
    private final Collection<? extends GrantedAuthority> authorities;

    public WMUserDetails(String username, String password, ERole role, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
    }

    // 账号未过期
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    // 账号未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 凭证未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账号已启用
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ERole getRole() {
        return role;
    }
}
