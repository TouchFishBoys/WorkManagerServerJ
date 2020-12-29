package com.my.workmanagement.repository;

import com.my.workmanagement.entity.StudentDO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<StudentDO, Integer> {
    StudentDO findByStudentId(Integer studentId);
    StudentDO findByStudentNum(String studentNumber);

    boolean existsByStudentId(Integer studentId);

    List<StudentDO> findAllByStudentClass(String studentClass, Pageable pageable);
}
