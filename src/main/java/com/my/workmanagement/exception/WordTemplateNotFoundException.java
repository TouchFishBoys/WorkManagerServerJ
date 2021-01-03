package com.my.workmanagement.exception;

import org.springframework.http.HttpStatus;

public class WordTemplateNotFoundException extends BaseException {
    public WordTemplateNotFoundException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Template not found");
    }
}
