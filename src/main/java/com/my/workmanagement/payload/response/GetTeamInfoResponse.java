package com.my.workmanagement.payload.response;

import com.my.workmanagement.model.bo.StudentInfoBO;

import java.io.Serializable;
import java.util.List;

public class GetTeamInfoResponse implements Serializable {
    private Integer teamId;
    private String teamName;
    // 人数
    private Integer memberCount;
    private List<StudentInfoBO> students;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public List<StudentInfoBO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentInfoBO> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "GetTeamInfoResponse{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", memberCount=" + memberCount +
                ", students=" + students +
                '}';
    }
}
