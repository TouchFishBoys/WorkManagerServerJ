package com.my.workmanagement.util;

import com.my.workmanagement.model.WMUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {
    public static WMUserDetails getUserDetail() {
        return (WMUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
