package com.my.workmanagement.repository;

import com.my.workmanagement.entity.TeacherDO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends PagingAndSortingRepository<TeacherDO, Integer> {

    TeacherDO findByTeacherNum(String teacherNum);

    TeacherDO findByTeacherId(Integer teacherId);

}
