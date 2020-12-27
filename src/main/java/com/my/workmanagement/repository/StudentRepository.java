package com.my.workmanagement.repository;

import com.my.workmanagement.entity.StudentDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<StudentDO, Integer> {
    StudentDO findByStudentNum(String studentNumber);
}
