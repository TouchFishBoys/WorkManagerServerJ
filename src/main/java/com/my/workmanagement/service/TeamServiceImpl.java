package com.my.workmanagement.service;

import com.my.workmanagement.exception.IdNotFoundException;
import com.my.workmanagement.model.bo.TeamInfoBO;
import com.my.workmanagement.service.interfaces.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Override
    public Integer getTeamId(Integer studentId, Integer courseId) throws IdNotFoundException {
        return null;
    }

    @Override
    public TeamInfoBO getTeamInfo(Integer teamId) {
        return null;
    }

    @Override
    public List<TeamInfoBO> getTeamInfoList(Integer courseId) {
        return null;
    }

    @Override
    public List<String> getTeamMembers(Integer teamId) {
        return null;
    }
}
