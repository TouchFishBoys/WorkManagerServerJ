package com.my.workmanagement.model.bo;

import java.util.List;

public class TeamInfoBO {
    private Integer teamId;
    private String teamName;
    private Integer memberCount;
    private Integer documentScore;
    private Integer score;
    private List<String> students;

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

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }


    public static final class TeamInfoBOBuilder {
        private Integer teamId;
        private String teamName;
        private Integer memberCount;
        private Integer documentScore;
        private Integer score;
        private List<String> students;

        private TeamInfoBOBuilder() {
        }

        public static TeamInfoBOBuilder aTeamInfoBO() {
            return new TeamInfoBOBuilder();
        }

        public TeamInfoBOBuilder withTeamId(Integer teamId) {
            this.teamId = teamId;
            return this;
        }

        public TeamInfoBOBuilder withTeamName(String teamName) {
            this.teamName = teamName;
            return this;
        }

        public TeamInfoBOBuilder withMemberCount(Integer memberCount) {
            this.memberCount = memberCount;
            return this;
        }

        public TeamInfoBOBuilder withDocumentScore(Integer documentScore) {
            this.documentScore = documentScore;
            return this;
        }

        public TeamInfoBOBuilder withScore(Integer score) {
            this.score = score;
            return this;
        }

        public TeamInfoBOBuilder withStudents(List<String> students) {
            this.students = students;
            return this;
        }

        public TeamInfoBO build() {
            TeamInfoBO teamInfoBO = new TeamInfoBO();
            teamInfoBO.setTeamId(teamId);
            teamInfoBO.setTeamName(teamName);
            teamInfoBO.setMemberCount(memberCount);
            teamInfoBO.setDocumentScore(documentScore);
            teamInfoBO.setScore(score);
            teamInfoBO.setStudents(students);
            return teamInfoBO;
        }
    }

    @Override
    public String toString() {
        return "TeamInfoBO{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", memberCount=" + memberCount +
                ", documentScore=" + documentScore +
                ", score=" + score +
                ", students=" + students +
                '}';
    }
}
