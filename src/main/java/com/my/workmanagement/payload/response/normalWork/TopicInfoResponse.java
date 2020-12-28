package com.my.workmanagement.payload.response.normalWork;

import java.sql.Timestamp;

public class TopicInfoResponse {

    //课程名（通过课程id获取）
    private String courseName;
    //题目名
    private String topicName;
    //题目描述
    private String topicDescription;
    //题目开始时间
    private Timestamp topicTimeStart;
    //题目截止时间
    private Timestamp topicTimeEnd;

    private TopicInfoResponse() {
    }

    public TopicInfoResponse(String courseName, String topicName, String topicDescription, Timestamp topicTimeStart, Timestamp topicTimeEnd) {
        this.courseName = courseName;
        this.topicName = topicName;
        this.topicDescription = topicDescription;
        this.topicTimeStart = topicTimeStart;
        this.topicTimeEnd = topicTimeEnd;

    }

    public String getCourseName() {
        return courseName;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public Timestamp getTopicTimeStart() {
        return topicTimeStart;
    }

    public Timestamp getTopicTimeEnd() {
        return topicTimeEnd;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public void setTopicTimeStart(Timestamp topicTimeStart) {
        this.topicTimeStart = topicTimeStart;
    }

    public void setTopicTimeEnd(Timestamp topicTimeEnd) {
        this.topicTimeEnd = topicTimeEnd;
    }

    public static final class TopicInfoResponseBuilder {
        private String courseName;
        private String topicName;
        private String topicDescription;
        private Timestamp topicTimeStart;
        private Timestamp topicTimeEnd;

        private TopicInfoResponseBuilder() {
        }

        public static TopicInfoResponse.TopicInfoResponseBuilder aTopicInfoResponse() { return new TopicInfoResponseBuilder(); }

        public TopicInfoResponse.TopicInfoResponseBuilder withCourseName(String courseName) {
            this.courseName = courseName;
            return this;
        }

        public TopicInfoResponse.TopicInfoResponseBuilder withTopicName(String topicName) {
            this.topicName = topicName;
            return this;
        }

        public TopicInfoResponse.TopicInfoResponseBuilder withTopicDescription(String topicDescription) {
            this.topicDescription = topicDescription;
            return this;
        }

        public TopicInfoResponse.TopicInfoResponseBuilder withTopicTimeStart(Timestamp topicTimeStart) {
            this.topicTimeStart = topicTimeStart;
            return this;
        }

        public TopicInfoResponse.TopicInfoResponseBuilder withTopicTimeEnd(Timestamp topicTimeEnd) {
            this.topicTimeEnd = topicTimeEnd;
            return this;
        }

        public TopicInfoResponse build() {
            TopicInfoResponse topicInfoResponse = new TopicInfoResponse();
            topicInfoResponse.setCourseName(this.courseName);
            topicInfoResponse.setTopicName(this.topicName);
            topicInfoResponse.setTopicDescription(this.topicDescription);
            topicInfoResponse.setTopicTimeStart(this.topicTimeStart);
            topicInfoResponse.setTopicTimeEnd(this.topicTimeEnd);
            return topicInfoResponse;
        }
    }

}
