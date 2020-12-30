package com.my.workmanagement.model.bo;

import java.util.Date;

public class TopicInfoBO {
    private Integer topicId; //题目Id
    private String topicName; //题目名
    private String topicDescription;//题目描述
    private Integer totalCount;//总人数
    private Integer finishedCount; //完成人数
    private Date startTime; //起始日期
    private Date finishTime; //截至日期

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

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getFinishedCount() {
        return finishedCount;
    }

    public void setFinishedCount(Integer finishedCount) {
        this.finishedCount = finishedCount;
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

    public static final class TopicInfoBOBuilder {
        private Integer topicId; //题目Id
        private String topicName; //题目名
        private String topicDescription;//题目描述
        private Integer totalCount;//总人数
        private Integer finishedCount; //完成人数
        private Date startTime; //起始日期
        private Date finishTime; //截至日期

        private TopicInfoBOBuilder() {
        }

        public static TopicInfoBOBuilder aTopicInfoBO() {
            return new TopicInfoBOBuilder();
        }

        public TopicInfoBOBuilder withTopicId(Integer topicId) {
            this.topicId = topicId;
            return this;
        }

        public TopicInfoBOBuilder withTopicName(String topicName) {
            this.topicName = topicName;
            return this;
        }

        public TopicInfoBOBuilder withTopicDescription(String topicDescription) {
            this.topicDescription = topicDescription;
            return this;
        }

        public TopicInfoBOBuilder withTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public TopicInfoBOBuilder withFinishedCount(Integer finishedCount) {
            this.finishedCount = finishedCount;
            return this;
        }

        public TopicInfoBOBuilder withStartTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        public TopicInfoBOBuilder withFinishTime(Date finishTime) {
            this.finishTime = finishTime;
            return this;
        }

        public TopicInfoBO build() {
            TopicInfoBO topicInfoBO = new TopicInfoBO();
            topicInfoBO.setTopicId(topicId);
            topicInfoBO.setTopicName(topicName);
            topicInfoBO.setTopicDescription(topicDescription);
            topicInfoBO.setTotalCount(totalCount);
            topicInfoBO.setFinishedCount(finishedCount);
            topicInfoBO.setStartTime(startTime);
            topicInfoBO.setFinishTime(finishTime);
            return topicInfoBO;
        }
    }

    @Override
    public String toString() {
        return "TopicInfoBO{" +
                "topicId=" + topicId +
                ", topicName='" + topicName + '\'' +
                ", topicDescription='" + topicDescription + '\'' +
                ", totalCount=" + totalCount +
                ", finishedCount=" + finishedCount +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
