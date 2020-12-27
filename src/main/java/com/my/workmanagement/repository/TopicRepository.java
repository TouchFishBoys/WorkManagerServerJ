package com.my.workmanagement.repository;

import com.my.workmanagement.entity.TopicDO;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<TopicDO,Integer> {
    TopicDO findByTopicId(Integer topicId);
}
