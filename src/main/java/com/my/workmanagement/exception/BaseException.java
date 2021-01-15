package com.my.workmanagement.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends Exception {
    private final HttpStatus status;

    public BaseException(HttpStatus status, String message){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
