package com.my.workmanagement.payload.request;

import com.my.workmanagement.model.bo.QaTableBO;

import java.util.List;

public class SubmitQaTableRequest {
    private List<QaTableBO.QaTableItemBO> qaList;

    public List<QaTableBO.QaTableItemBO> getQaList() {
        return qaList;
    }

    public void setQaList(List<QaTableBO.QaTableItemBO> qaList) {
        this.qaList = qaList;
    }

    @Override
    public String toString() {
        return "SubmitQaTableRequest{" +
                "qaList=" + qaList +
                '}';
    }
}
