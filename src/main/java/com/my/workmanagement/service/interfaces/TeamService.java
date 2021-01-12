package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.StudentInfoBO;
import com.my.workmanagement.model.bo.TeamInfoBO;

import java.util.List;

public interface TeamService {
    /**
     * 获取队伍的Id
     *
     * @param studentId 学生Id
     * @param courseId  课程Id
     * @return 队伍Id
     * @throws IdNotFoundException 找不到对应记录
     */
    Integer getTeamId(Integer studentId, Integer courseId) throws IdNotFoundException;

    TeamInfoBO getTeamInfo(Integer teamId) throws IdNotFoundException;

    /**
     * 获取课程下所有的小组
     *
     * @param courseId 课程Id
     * @return 队伍信息列表
     */
    List<TeamInfoBO> getTeamInfoList(Integer courseId) throws IdNotFoundException;

    /**
     * 获取小组成员信息列表
     *
     * @param teamId 队伍Id
     * @return /
     * @throws IdNotFoundException 没找到小组
     */
    List<StudentInfoBO> getTeamMembers(Integer teamId) throws IdNotFoundException;

    List<String> getTeamMembersName(Integer teamId) throws IdNotFoundException;

    Integer getFinalWorkId(Integer teamId) throws IdNotFoundException;

    Integer createTeam(String teamName, Integer studentId, Integer courseId);

    List<TeamInfoBO> getTeamInfoByCourseId(Integer courseId) throws IdNotFoundException;

    Integer joinTeam(Integer studentId,Integer courseId,Integer teamId);
}
