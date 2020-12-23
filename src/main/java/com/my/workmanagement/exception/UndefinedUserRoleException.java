package com.my.workmanagement.exception;

import org.springframework.http.HttpStatus;

public class UndefinedUserRoleException extends BaseException {
    public UndefinedUserRoleException(String roleName) {
        super(HttpStatus.BAD_REQUEST, "Undefined role: " + roleName);
    }
}
