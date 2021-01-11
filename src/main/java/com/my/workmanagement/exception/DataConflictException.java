package com.my.workmanagement.exception;

import org.springframework.http.HttpStatus;

public class DataConflictException extends BaseException {
    public DataConflictException(String dataName) {
        super(HttpStatus.CONFLICT, dataName + " is conflict");
    }
}
