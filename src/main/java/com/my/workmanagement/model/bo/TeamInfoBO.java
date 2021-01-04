package com.my.workmanagement.model.bo;

public class TeamInfoBO {
    private Integer teamId;
    private String teamName;
    private Integer memberCount;
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

    public Integer getDocumentScore() {
        return documentScore;
    }

    public void setDocumentScore(Integer documentScore) {
        this.documentScore = documentScore;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
