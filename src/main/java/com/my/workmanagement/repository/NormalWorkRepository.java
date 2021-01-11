package com.my.workmanagement.repository;

import com.my.workmanagement.entity.NormalWorkDO;
import com.my.workmanagement.entity.StudentDO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NormalWorkRepository extends CrudRepository<NormalWorkDO, Integer> {

    Integer countAllByStudent(StudentDO studentDO);

    Integer countAllByTopic_Course_CourseId(Integer courseId);

    Integer countAllByTopic_Course_CourseIdAndStudent_StudentId(Integer courseId, Integer studentId);

    boolean existsByTopic_TopicIdAndStudent_StudentId(Integer topicId, Integer studentId);

    NormalWorkDO getFirstByTopic_TopicIdAndStudent_StudentId(Integer topicId, Integer studentId);

    List<NormalWorkDO> findAllByTopic_TopicId(Integer TopicId);

    List<NormalWorkDO> findAllByStudent_StudentId(Integer studentId);

    Integer countAllByStudent_StudentId(Integer studentId);

    @Modifying
    @Query("UPDATE NormalWorkDO nwork SET nwork.nworkScore = :score WHERE nwork.nworkId = :id")
    int setScoreById(Integer id, Integer score);

    int countAllByTopic_TopicId(Integer topicId);
}
