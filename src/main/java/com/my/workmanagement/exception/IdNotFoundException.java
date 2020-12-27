package com.my.workmanagement.exception;

import org.springframework.http.HttpStatus;

public class IdNotFoundException extends BaseException {
    public IdNotFoundException(String idName) {
        super(HttpStatus.NOT_FOUND, "Cant find " + idName);
    }
}
