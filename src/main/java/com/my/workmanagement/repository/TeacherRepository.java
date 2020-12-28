package com.my.workmanagement.repository;

import com.my.workmanagement.entity.TeacherDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends CrudRepository<TeacherDO, Integer> {

    TeacherDO findByTeacherNum(String teacherNum);

    TeacherDO findByTeacherId(Integer teacherId);

}
