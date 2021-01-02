package com.my.workmanagement.payload.response.teacher;

public class TeacherInfoResponse {
    //教师姓名
    private String teacherName;
    //教师工号
    private String teacherNum;
    //教师电话
    private String teacherTell;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(String teacherNum) {
        this.teacherNum = teacherNum;
    }

    public String getTeacherTell() {
        return teacherTell;
    }

    public void setTeacherTell(String teacherTell) {
        this.teacherTell = teacherTell;
    }

    public static final class TeacherInfoResponseBuilder {
        //教师姓名
        private String teacherName;
        //教师工号
        private String teacherNum;
        //教师电话
        private String teacherTell;

        private TeacherInfoResponseBuilder() {
        }

        public static TeacherInfoResponseBuilder aTeacherInfoResponse() {
            return new TeacherInfoResponseBuilder();
        }

        public TeacherInfoResponseBuilder withTeacherName(String teacherName) {
            this.teacherName = teacherName;
            return this;
        }

        public TeacherInfoResponseBuilder withTeacherNum(String teacherNum) {
            this.teacherNum = teacherNum;
            return this;
        }

        public TeacherInfoResponseBuilder withTeacherTell(String teacherTell) {
            this.teacherTell = teacherTell;
            return this;
        }

        public TeacherInfoResponse build() {
            TeacherInfoResponse teacherInfoResponse = new TeacherInfoResponse();
            teacherInfoResponse.setTeacherName(teacherName);
            teacherInfoResponse.setTeacherNum(teacherNum);
            teacherInfoResponse.setTeacherTell(teacherTell);
            return teacherInfoResponse;
        }
    }

    @Override
    public String toString() {
        return "TeacherInfoResponse{" +
                "teacherName='" + teacherName + '\'' +
                ", teacherNum='" + teacherNum + '\'' +
                ", teacherTell='" + teacherTell + '\'' +
                '}';
    }
}
