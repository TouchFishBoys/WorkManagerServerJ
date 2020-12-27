package com.my.workmanagement.payload;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResponseResult {
    FAILED("failed"), SUCCESS("success");

    @JsonValue
    private final String displayedName;

    ResponseResult(String displayedName) {
        this.displayedName = displayedName;
    }

    public String getDisplayedName() {
        return displayedName;
    }
}
