package com.my.workmanagement.repository;

import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.entity.CourseSelectionDO;
import com.my.workmanagement.entity.StudentDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface CourseSelectionRepository extends CrudRepository<CourseSelectionDO, Integer> {
    List<CourseSelectionDO> findAllByStudent(StudentDO student);

    List<CourseSelectionDO> findAllByStudent_StudentId(Integer studentId);

    Integer countAllByCourse_CourseId(Integer courseId);

    //@Query(value = "SELECT COUNT(CourseSelectionDO.team) FROM CourseSelectionDO cs WHERE cs.course.courseId = ?1 GROUP BY cs.team.teamId")
    //Integer countAllByCourseIdGroupByTeam(Integer courseId);

    List<CourseSelectionDO> getAllByTeam_TeamId(Integer teamId);

    Integer countAllByCourse_CourseIdAndTeam_FinalWork_TimeUploadNot(Integer courseId, Timestamp timestamp);
/*
    @Query("SELECT TeamDO.teamId FROM CourseSelectionDO cs WHERE cs.student.studentId = :studentId AND cs.course.courseId = :courseId")
    Integer getTeamIdByStudentIdAndCourseId(Integer studentId, Integer courseId);
*/
    List<CourseSelectionDO> findAllByCourse_CourseId(Integer courseId);
}
