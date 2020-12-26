package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.model.ERole;

public interface AuthService {
    String login(String username, String password, ERole userRole);
}
