package com.my.workmanagement.payload.response;

import com.my.workmanagement.model.bo.TopicInfoBO;

import java.util.List;

public class TopicInfoListResponse {
    private List<TopicInfoBO> topics;

    public List<TopicInfoBO> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicInfoBO> topics) {
        this.topics = topics;
    }

    public void addTopic(TopicInfoBO topicInfo) {
        this.topics.add(topicInfo);
    }
}
