package com.my.workmanagement.exception;

import org.springframework.http.HttpStatus;

import java.io.IOException;

public class StorageIOException extends BaseException {
    public StorageIOException(IOException e) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
