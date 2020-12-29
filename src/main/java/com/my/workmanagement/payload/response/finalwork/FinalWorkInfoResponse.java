package com.my.workmanagement.payload.response.finalwork;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.my.workmanagement.model.bo.FinalWorkBO;
import com.my.workmanagement.model.status.FinalWorkStatus;

public class FinalWorkInfoResponse {
    private Integer fworkId;

    private FinalWorkBO detail;

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private FinalWorkStatus status;


    public static final class FinalWorkInfoResponseBuilder {
        private Integer fworkId;
        private FinalWorkBO detail;
        private FinalWorkStatus status;

        private FinalWorkInfoResponseBuilder() {
        }

        public static FinalWorkInfoResponseBuilder aFinalWorkInfoResponse() {
            return new FinalWorkInfoResponseBuilder();
        }

        public FinalWorkInfoResponseBuilder withFworkId(Integer fworkId) {
            this.fworkId = fworkId;
            return this;
        }

        public FinalWorkInfoResponseBuilder withDetail(FinalWorkBO detail) {
            this.detail = detail;
            return this;
        }

        public FinalWorkInfoResponseBuilder withStatus(FinalWorkStatus status) {
            this.status = status;
            return this;
        }

        public FinalWorkInfoResponse build() {
            FinalWorkInfoResponse finalWorkInfoResponse = new FinalWorkInfoResponse();
            finalWorkInfoResponse.fworkId = this.fworkId;
            finalWorkInfoResponse.detail = this.detail;
            finalWorkInfoResponse.status = this.status;
            return finalWorkInfoResponse;
        }
    }
}
