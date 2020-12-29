package com.my.workmanagement.model.bo;

import java.util.Date;

public class TopicInfoBO {
    private Integer topicId; //题目Id
    private String topicName; //题目名
    private String finishedCount; //完成人数
    private Date startTime; //起始日期
    private Date finishTime; //截至日期

    public String getFinishedCount() {
        return finishedCount;
    }

    public void setFinishedCount(String finishedCount) {
        this.finishedCount = finishedCount;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
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
                ", topicName='" + topicName + '\'' +
                ", finishedCount='" + finishedCount + '\'' +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
