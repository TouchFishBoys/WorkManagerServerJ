package com.my.workmanagement.exception;

import org.springframework.http.HttpStatus;

public class StorageFileNotFoundException extends BaseException {

    public StorageFileNotFoundException() {
        super(HttpStatus.NOT_FOUND, "找不到文件");
    }
}
