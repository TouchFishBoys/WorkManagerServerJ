package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.TeacherInfoBO;

import javax.persistence.criteria.CriteriaBuilder;

public interface TeacherService {
    TeacherInfoBO getTeacherInfo(Integer teacherId)throws IdNotFoundException;
}
