package com.my.workmanagement.payload.response;

import com.my.workmanagement.model.bo.StudentInfoBO;

import java.util.List;

public class GetTeamMembersResponse {
    String teamName;

    private List<StudentInfoBO> members;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<StudentInfoBO> getMembers() {
        return members;
    }

    public void setMembers(List<StudentInfoBO> members) {
        this.members = members;
    }
}
