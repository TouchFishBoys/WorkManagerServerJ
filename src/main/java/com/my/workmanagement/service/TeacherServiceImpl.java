package com.my.workmanagement.service;

import com.my.workmanagement.entity.TeacherDO;
import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.TeacherInfoBO;
import com.my.workmanagement.repository.TeacherRepository;
import com.my.workmanagement.service.interfaces.AuthService;
import com.my.workmanagement.service.interfaces.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public TeacherInfoBO getTeacherInfo(Integer teacherId) throws IdNotFoundException {
        TeacherDO teacherDO = teacherRepository.findByTeacherId(teacherId);
        if (teacherDO == null) {
            throw new IdNotFoundException("teacherId:" + teacherId.toString());
        }
        return TeacherInfoBO.TeacherInfoBOBuilder.aTeacherInfoBO()
                .withTeacherId(teacherDO.getTeacherId())
                .withTeacherName(teacherDO.getTeacherName())
                .withTeacherNum(teacherDO.getTeacherNum())
                .withTeacherTell(teacherDO.getTeacherTell())
                .build();
    }

}
