package com.my.workmanagement.repository;

import com.my.workmanagement.entity.CourseDO;
import com.my.workmanagement.entity.CourseSelectionDO;
import com.my.workmanagement.entity.StudentDO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseSelectionRepository extends CrudRepository<CourseSelectionDO, Integer> {
}
