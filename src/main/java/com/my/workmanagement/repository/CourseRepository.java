package com.my.workmanagement.repository;

import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.entity.TeacherDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends PagingAndSortingRepository<CourseDO, Integer> {
    CourseDO findByCourseId(Integer courseId);

    @Query("SELECT count(StudentDO.studentId) FROM CourseSelectionDO WHERE CourseDO.courseId = :courseId")
    Integer getStuNum(@Param("courseId") Integer courseId);
    
    List<CourseDO> findAllByTeacherOrderByCourseId(TeacherDO teacher);

}
