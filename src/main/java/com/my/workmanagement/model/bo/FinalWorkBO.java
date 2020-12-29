package com.my.workmanagement.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class FinalWorkBO {
    private Integer finalWorkId;
    private String finalWorkName; // 大作业名称
    private List<String> authors; // 作者（学生姓名）
    private String teamName; // 小队名称
    private String description; // 大作业描述
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date submitTime; // 提交时间

    public Integer getFinalWorkId() {
        return finalWorkId;
    }

    public String getFinalWorkName() {
        return finalWorkName;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getDescription() {
        return description;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setFinalWorkId(Integer finalWorkId) {
        this.finalWorkId = finalWorkId;
    }

    public void setFinalWorkName(String finalWorkName) {
        this.finalWorkName = finalWorkName;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }


    public static final class FinalWorkBOBuilder {
        private Integer finalWorkId;
        private String finalWorkName; // 大作业名称
        private List<String> authors; // 作者（学生姓名）
        private String teamName; // 小队名称
        private String description; // 大作业描述
        private Date submitTime; // 提交时间

        private FinalWorkBOBuilder() {
        }

        public static FinalWorkBOBuilder aFinalWorkBO() {
            return new FinalWorkBOBuilder();
        }

        public FinalWorkBOBuilder withFinalWorkId(Integer finalWorkId) {
            this.finalWorkId = finalWorkId;
            return this;
        }

        public FinalWorkBOBuilder withFinalWorkName(String finalWorkName) {
            this.finalWorkName = finalWorkName;
            return this;
        }

        public FinalWorkBOBuilder withAuthors(List<String> authors) {
            this.authors = authors;
            return this;
        }

        public FinalWorkBOBuilder withTeamName(String teamName) {
            this.teamName = teamName;
            return this;
        }

        public FinalWorkBOBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public FinalWorkBOBuilder withSubmitTime(Date submitTime) {
            this.submitTime = submitTime;
            return this;
        }

        public FinalWorkBO build() {
            FinalWorkBO finalWorkBO = new FinalWorkBO();
            finalWorkBO.setFinalWorkId(finalWorkId);
            finalWorkBO.setFinalWorkName(finalWorkName);
            finalWorkBO.setAuthors(authors);
            finalWorkBO.setTeamName(teamName);
            finalWorkBO.setDescription(description);
            finalWorkBO.setSubmitTime(submitTime);
            return finalWorkBO;
        }
    }
}
