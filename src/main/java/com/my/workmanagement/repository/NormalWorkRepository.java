package com.my.workmanagement.repository;

import com.my.workmanagement.entity.NormalWorkDO;
import com.my.workmanagement.entity.StudentDO;
import com.my.workmanagement.entity.TopicDO;
import org.springframework.data.repository.CrudRepository;

public interface NormalWorkRepository extends CrudRepository<NormalWorkDO,Integer> {

    Integer countAllByStudent(StudentDO studentDO);
    Integer countAllByTopic_CourseId(Integer courseId);
    Integer countAllByTopic_CourseIdAndStudent_StudentId(Integer courseId,Integer studentId);
}
