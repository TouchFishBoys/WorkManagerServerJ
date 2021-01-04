package com.my.workmanagement.payload.request;

import com.my.workmanagement.model.bo.QaTableBO;

import java.util.List;

public class SubmitQaTableRequest {
    private String qaLocation;
    private Integer score;
    private List<QaTableBO.QaTableItemBO> qaList;

    public List<QaTableBO.QaTableItemBO> getQaList() {
        return qaList;
    }

    public void setQaList(List<QaTableBO.QaTableItemBO> qaList) {
        this.qaList = qaList;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getQaLocation() {
        return qaLocation;
    }

    public void setQaLocation(String qaLocation) {
        this.qaLocation = qaLocation;
    }

    @Override
    public String toString() {
        return "SubmitQaTableRequest{" +
                "qaList=" + qaList +
                '}';
    }
}
