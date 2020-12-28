package com.my.workmanagement.repository;

import com.my.workmanagement.entity.TopicDO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TopicRepository extends CrudRepository<TopicDO,Integer> {
    TopicDO findByTopicId(Integer topicId);
    List<TopicDO> findAllByCourseId(Integer courseId);
    Integer countAllByCourseId(Integer courseId);
}
