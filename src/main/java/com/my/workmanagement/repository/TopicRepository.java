package com.my.workmanagement.repository;

import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.entity.TopicDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TopicRepository extends CrudRepository<TopicDO, Integer> {
    TopicDO findByTopicId(Integer topicId);

    List<TopicDO> findAllByCourse(CourseDO course);

    List<TopicDO> findAllByCourse_CourseId(Integer courseId);

    Integer countAllByCourse(CourseDO courseDO);

    Integer countAllByCourse_CourseId(Integer courseId);
}
