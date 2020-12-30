package com.my.workmanagement.payload.request.finalwork;

import org.hibernate.validator.constraints.Range;

public class SetDocumentScoreRequest {
    @Range(min = 0, max = 100, message = "Score must range in [0,100]")
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
