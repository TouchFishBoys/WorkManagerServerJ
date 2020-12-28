package com.my.workmanagement.payload.response.finalWork;

import com.my.workmanagement.entity.TeamDO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

public class FinalWorkInfoResponse {
    //大作业id
    private Integer fworkId;
    //大作业名
    private String fworkName;
    //大作业描述
    private String fworkDescription;
    //小组id
    private Integer teamId;
    //小组名
    private String teamName;
    //大作业成绩
    private Integer fworkScore;
    //上传时间
    private Timestamp timeUpload;

    private FinalWorkInfoResponse() {
    }


    public FinalWorkInfoResponse(
            Integer fworkId
            , String fworkName
            , String fworkDescription
            , Integer teamId
            , String teamName
            , Integer fworkScore
            , Timestamp timeUpload) {
        this.fworkId = fworkId;
        this.fworkName = fworkName;
        this.fworkDescription = fworkDescription;
        this.teamId = teamId;
        this.teamName = teamName;
        this.fworkScore = fworkScore;
        this.timeUpload = timeUpload;
    }

    public Integer getFworkId() {
        return fworkId;
    }

    public void setFworkId(Integer fworkId) {
        this.fworkId = fworkId;
    }

    public String getFworkName() {
        return fworkName;
    }

    public void setFworkName(String fworkName) {
        this.fworkName = fworkName;
    }

    public String getFworkDescription() {
        return fworkDescription;
    }

    public void setFworkDescription(String fworkDescription) {
        this.fworkDescription = fworkDescription;
    }

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

    public Integer getFworkScore() {
        return fworkScore;
    }

    public void setFworkScore(Integer fworkScore) {
        this.fworkScore = fworkScore;
    }

    public Timestamp getTimeUpload() {
        return timeUpload;
    }

    public void setTimeUpload(Timestamp timeUpload) {
        this.timeUpload = timeUpload;
    }

    public final static class FinalWorkInfoResponseBuilder {
        private Integer fworkId;
        private String fworkName;
        private String fworkDescription;
        private Integer teamId;
        private String teamName;
        private Integer fworkScore;
        private Timestamp timeUpload;

        public static FinalWorkInfoResponse.FinalWorkInfoResponseBuilder aFinalWorkInfoResponse() {
            return new FinalWorkInfoResponseBuilder();
        }


        public FinalWorkInfoResponse.FinalWorkInfoResponseBuilder withFworkId(Integer fworkId) {
            this.fworkId = fworkId;
            return this;
        }

        public FinalWorkInfoResponse.FinalWorkInfoResponseBuilder withFworkName(String fworkName) {
            this.fworkName = fworkName;
            return this;
        }

        public FinalWorkInfoResponse.FinalWorkInfoResponseBuilder withFworkDescreption(String fworkDescription) {
            this.fworkDescription = fworkDescription;
            return this;
        }

        public FinalWorkInfoResponse.FinalWorkInfoResponseBuilder withTeamId(Integer teamId) {
            this.teamId = teamId;
            return this;
        }

        public FinalWorkInfoResponse.FinalWorkInfoResponseBuilder withTeamName(String teamName) {
            this.teamName = teamName;
            return this;
        }

        public FinalWorkInfoResponse.FinalWorkInfoResponseBuilder withFworkScore(Integer fworkScore) {
            this.fworkScore = fworkScore;
            return this;
        }

        public FinalWorkInfoResponse.FinalWorkInfoResponseBuilder withTimeUpload(Timestamp timeUpload) {
            this.timeUpload = timeUpload;
            return this;
        }

        public FinalWorkInfoResponse build() {
            FinalWorkInfoResponse finalWorkInfoResponse = new FinalWorkInfoResponse();
            finalWorkInfoResponse.setFworkId(this.fworkId);
            finalWorkInfoResponse.setFworkName(this.fworkName);
            finalWorkInfoResponse.setFworkDescription(this.fworkDescription);
            finalWorkInfoResponse.setTeamId(this.teamId);
            finalWorkInfoResponse.setTeamName(this.teamName);
            finalWorkInfoResponse.setFworkScore(this.fworkScore);
            finalWorkInfoResponse.setTimeUpload(this.timeUpload);
            return finalWorkInfoResponse;
        }

    }
}
