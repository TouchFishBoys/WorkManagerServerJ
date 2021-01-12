package com.my.workmanagement.controller;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.StudentInfoBO;
import com.my.workmanagement.model.bo.TeamInfoBO;
import com.my.workmanagement.payload.PackedResponse;
import com.my.workmanagement.payload.request.SingleValueRequest;
import com.my.workmanagement.payload.response.GetTeamInfoResponse;
import com.my.workmanagement.service.interfaces.StudentService;
import com.my.workmanagement.service.interfaces.TeamService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
    @ApiOperation("获取队伍信息")
    @GetMapping("/{teamId}")
    public ResponseEntity<PackedResponse<GetTeamInfoResponse>> getTeamInfo(
            @PathVariable Integer teamId
    ) throws IdNotFoundException {
        GetTeamInfoResponse response = new GetTeamInfoResponse();
        TeamInfoBO teamInfo = teamService.getTeamInfo(teamId);
        Integer finalWorkId = teamService.getFinalWorkId(teamId);

        response.setTeamId(teamInfo.getTeamId());
        response.setMemberCount(teamInfo.getMemberCount());
        response.setTeamName(teamInfo.getTeamName());
        response.setScore(teamInfo.getScore());
        response.setDocumentScore(teamInfo.getDocumentScore());
        response.setFinalWorkId(finalWorkId);
        response.setStudents(studentService.getStudentsByTeamId(teamId));

        return PackedResponse.success(response, "Success");
    }

    /**
     * 获取队伍成员信息
     *
     * @param teamId 队伍
     * @return /
     */
    @ApiOperation("获取队伍成员（详细）")
    @GetMapping("/{teamId}/student")
    public ResponseEntity<PackedResponse<List<StudentInfoBO>>> getTeamMemberList(
            @PathVariable Integer teamId
    ) throws IdNotFoundException {
        List<StudentInfoBO> studentInfoList = studentService.getStudentsByTeamId(teamId);

        return PackedResponse.success(studentInfoList, "Success");
    }

    /**
     * 创建队伍
     *
     * @param studentId 学生ID
     * @param courseId  课程ID
     * @return /
     */
    @ApiOperation("创建队伍")
    @PostMapping("/{studentId}/{courseId}")
    public ResponseEntity<PackedResponse<Integer>> createTeam(
            @PathVariable Integer studentId,
            @PathVariable Integer courseId,
            @ApiParam("队伍名") @RequestBody SingleValueRequest<String> request
    ) throws IdNotFoundException {
        return PackedResponse.success(teamService.createTeam(request.getValue(), studentId, courseId), "success");
    }

    /**
     * 查看当前课程队伍信息
     *
     * @param courseId 课程ID
     * @return /
     */
    @ApiOperation("获取队伍信息")
    @GetMapping("/{courseId}/course")
    public ResponseEntity<PackedResponse<List<TeamInfoBO>>> getTeamInfoByCourseId(
            @PathVariable Integer courseId
    ) throws IdNotFoundException {
        List<TeamInfoBO> response = teamService.getTeamInfoByCourseId(courseId);
        return PackedResponse.success(response, "");
    }

    /**
     * 加入队伍
     *
     * @param courseId  课程ID
     * @param teamId    队伍ID
     * @return /
     */
    @ApiOperation("加入队伍")
    @PatchMapping("/{courseId}/{teamId}")
    public ResponseEntity<PackedResponse<Integer>> joinTeam(
            @PathVariable Integer courseId,
            @PathVariable Integer teamId
    ) throws IdNotFoundException {
        Integer response = teamService.joinTeam(courseId,teamId);
        return PackedResponse.success(response, "success");
    }
}
