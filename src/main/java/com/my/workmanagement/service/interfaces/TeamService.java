package com.my.workmanagement.service.interfaces;

import com.my.workmanagement.exception.IdNotFoundException;
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

    TeamInfoBO getTeamInfo(Integer teamId);

    /**
     * 获取课程下所有的小组
     *
     * @param courseId 课程Id
     * @return 队伍信息列表
     */
    List<TeamInfoBO> getTeamInfoList(Integer courseId);

    List<String> getTeamMembers(Integer teamId);

}
