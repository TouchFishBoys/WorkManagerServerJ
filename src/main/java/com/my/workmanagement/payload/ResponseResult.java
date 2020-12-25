package com.my.workmanagement.payload;

public enum ResponseResult {
    FAILED("failed"), SUCCESS("success");

    private final String displayedName;

    ResponseResult(String displayedName) {
        this.displayedName = displayedName;
    }

    public String getDisplayedName() {
        return displayedName;
    }
}
