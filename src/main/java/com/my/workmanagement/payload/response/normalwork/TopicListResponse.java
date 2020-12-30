package com.my.workmanagement.payload.response.normalwork;

import com.my.workmanagement.model.bo.TopicInfoBO;

import java.util.List;

public class TopicListResponse {
    private List<TopicInfoBO> topicList;
    public TopicListResponse(List<TopicInfoBO> topicList){
        this.topicList=topicList;
    }

    public List<TopicInfoBO> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<TopicInfoBO> topicList) {
        this.topicList = topicList;
    }
}
