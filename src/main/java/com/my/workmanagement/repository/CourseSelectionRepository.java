package com.my.workmanagement.repository;

import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.entity.CourseSelectionDO;
import com.my.workmanagement.entity.StudentDO;
import com.my.workmanagement.entity.TeamDO;
import com.my.workmanagement.entity.upk.CourseSelectionUPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseSelectionRepository extends CrudRepository<CourseSelectionDO, CourseSelectionUPK> {
    List<CourseSelectionDO> findAllByStudent(StudentDO student);

    List<CourseSelectionDO> findAllByStudent_StudentId(Integer studentId);

    Integer countAllByCourse_CourseId(Integer courseId);
    Integer countAllByCourse(CourseDO course);

    List<CourseSelectionDO> getAllByTeam_TeamId(Integer teamId);

    // 大作业完成人数
    @Query("SELECT COUNT(tdo) FROM CourseSelectionDO AS cs, TeamDO AS tdo, FinalWorkDO AS fwork WHERE fwork.timeUpload IS NOT NULL AND cs.course.courseId = :courseId")
    Integer countAllByCourse_CourseIdAndTeam_FinalWork_TimeUploadNotNull(Integer courseId);

    List<CourseSelectionDO> findAllByCourseAndTeamIsNotNull(CourseDO course);

    List<CourseSelectionDO> findAllByCourse_CourseId(Integer courseId);

    CourseSelectionDO findFirstByStudent_StudentIdAndCourse_CourseId(Integer studentId, Integer courseId);

    CourseSelectionDO getFirstByTeam(TeamDO team);

    List<CourseSelectionDO> findAllByTeam_TeamId(Integer TeamId);

    @Modifying
    @Query(value = "INSERT INTO course_selection(course_id, stu_id, gmt_create, gmt_modified) VALUES (?1, ?2, NOW(), NOW())", nativeQuery = true)
    int insert(Integer courseId, Integer studentId);
}
