package com.my.workmanagement.controller;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.StudentInfoBO;
import com.my.workmanagement.model.bo.TeacherInfoBO;
import com.my.workmanagement.payload.response.student.StudentInfoResponse;
import com.my.workmanagement.payload.response.teacher.TeacherInfoResponse;
import com.my.workmanagement.service.interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teacher")
public class TeacherController {
    private TeacherService teacherService;

    @Autowired
    TeacherController(TeacherService teacherService){
        this.teacherService=teacherService;
    }
    /**
     * 获取教师信息
     *
     * @param teacherId 教师 Id
     * @return 教师信息
     */
    @GetMapping(value = "/{teacherId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherInfoResponse> getTeacherInfo(
            @PathVariable Integer teacherId
    ) throws IdNotFoundException {
        TeacherInfoBO teacherInfoBO=teacherService.getTeacherInfo(teacherId);

        TeacherInfoResponse response= TeacherInfoResponse.TeacherInfoResponseBuilder.aTeacherInfoResponse()
                .withTeacherName(teacherInfoBO.getTeacherName())
                .withTeacherNum(teacherInfoBO.getTeacherNum())
                .withTeacherTell(teacherInfoBO.getTeacherTell())
                .build();
        return ResponseEntity.ok(response);
    }
}
