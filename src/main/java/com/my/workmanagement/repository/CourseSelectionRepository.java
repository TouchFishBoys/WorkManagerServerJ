package com.my.workmanagement.repository;

import com.my.workmanagement.entity.CourseSelectionDO;
import com.my.workmanagement.entity.StudentDO;
import com.my.workmanagement.entity.upk.CourseSelectionUPK;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface CourseSelectionRepository extends CrudRepository<CourseSelectionDO, CourseSelectionUPK> {
    List<CourseSelectionDO> findAllByStudent(StudentDO student);

    List<CourseSelectionDO> findAllByStudent_StudentId(Integer studentId);

    Integer countAllByCourse_CourseId(Integer courseId);

    List<CourseSelectionDO> getAllByTeam_TeamId(Integer teamId);

    Integer countAllByCourse_CourseIdAndTeam_FinalWork_TimeUploadNot(Integer courseId, Timestamp timestamp);

    List<CourseSelectionDO> findAllByCourse_CourseId(Integer courseId);

    CourseSelectionDO findFirstByStudent_StudentIdAndCourse_CourseId(Integer studentId, Integer courseId);
}
