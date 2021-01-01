package com.my.workmanagement.controller;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.StudentInfoBO;
import com.my.workmanagement.payload.PackedResponse;
import com.my.workmanagement.service.interfaces.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/team")
public class TeamController {
    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
    private final StudentService studentService;

    @Autowired
    public TeamController(
            StudentService studentService
    ) {
        this.studentService = studentService;
    }

    /**
     * 获取队伍成员信息
     *
     * @param teamId 队伍
     * @return
     */
    @GetMapping("/{teamId}")
    public ResponseEntity<PackedResponse<List<StudentInfoBO>>> getTeamMemberList(
            @PathVariable Integer teamId
    ) throws IdNotFoundException {
        List<StudentInfoBO> studentInfoList = studentService.getStudentsByTeamId(teamId);

        return PackedResponse.success(studentInfoList, "Success");
    }
}
