package com.my.workmanagement.model.status;

public enum FinalWorkStatus {
    RELEASED("released", "已发布"),
    SUBMITED("submitted", "已提交"),
    SCORED("scored", "已评分");

    private String code;
    private String message;

    FinalWorkStatus(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
