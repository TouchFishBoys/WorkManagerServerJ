package com.my.workmanagement.payload.request.finalwork;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SetFinalScoreRequest {
    @NotNull(message = "Please provide score")
    @Min(value = 0, message = "Min score is 0")
    @Max(value = 100, message = "Max score is 100")
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
