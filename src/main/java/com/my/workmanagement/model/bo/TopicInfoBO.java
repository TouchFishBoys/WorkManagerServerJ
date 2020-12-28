package com.my.workmanagement.model.bo;

import java.io.Serializable;
import java.util.Date;

public class TopicInfoBO {
    private Integer topicId;
    private Integer topicName;
    private Date startTime;
    private Date finishTime;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getTopicName() {
        return topicName;
    }

    public void setTopicName(Integer topicName) {
        this.topicName = topicName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "TopicInfoBO{" +
                "topicId=" + topicId +
                ", topicName=" + topicName +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
