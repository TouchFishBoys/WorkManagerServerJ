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
    private Integer documentScore;
    private Integer score;

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getDocumentScore() {
        return documentScore;
    }

    public void setDocumentScore(Integer documentScore) {
        this.documentScore = documentScore;
    }

    @Override
    public String toString() {
        return "GetTeamInfoResponse{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", memberCount=" + memberCount +
                ", students=" + students +
                ", documentScore=" + documentScore +
                ", score=" + score +
                '}';
    }
}
