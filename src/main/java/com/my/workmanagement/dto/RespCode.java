package com.my.workmanagement.dto;

public enum RespCode {
    SUCCESS(0, "成功"),
    ERROR(1, "错误");

    private final int code;
    private final String msg;

    RespCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
