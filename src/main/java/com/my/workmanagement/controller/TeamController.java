package com.my.workmanagement.controller;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.StudentInfoBO;
import com.my.workmanagement.model.bo.TeamInfoBO;
import com.my.workmanagement.payload.PackedResponse;
import com.my.workmanagement.payload.response.GetTeamInfoResponse;
import com.my.workmanagement.service.interfaces.StudentService;
import com.my.workmanagement.service.interfaces.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

    private final StudentService studentService;
    private final TeamService teamService;

    @Autowired
    public TeamController(
            StudentService studentService,
            TeamService teamService
    ) {
        this.studentService = studentService;
        this.teamService = teamService;
    }

    /**
     * 获取队伍信息
     *
     * @param teamId 队伍Id
     * @return 队伍信息
     */
    @GetMapping("/{teamId}")
    public ResponseEntity<PackedResponse<GetTeamInfoResponse>> getTeamInfo(
            @PathVariable Integer teamId
    ) throws IdNotFoundException {
        GetTeamInfoResponse response = new GetTeamInfoResponse();
        TeamInfoBO teamInfo = teamService.getTeamInfo(teamId);

        response.setTeamId(teamInfo.getTeamId());
        response.setMemberCount(teamInfo.getMemberCount());
        response.setTeamName(teamInfo.getTeamName());
        response.setScore(teamInfo.getScore());
        response.setDocumentScore(teamInfo.getDocumentScore());

        return PackedResponse.success(response, "Success");
    }

    /**
     * 获取队伍成员信息
     *
     * @param teamId 队伍
     * @return /
     */
    @GetMapping("/{teamId}/student")
    public ResponseEntity<PackedResponse<List<StudentInfoBO>>> getTeamMemberList(
            @PathVariable Integer teamId
    ) throws IdNotFoundException {
        List<StudentInfoBO> studentInfoList = studentService.getStudentsByTeamId(teamId);

        return PackedResponse.success(studentInfoList, "Success");
    }
}
