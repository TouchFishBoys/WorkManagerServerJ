package com.my.workmanagement.model.bo;

import java.io.Serializable;

public class TeacherInfoBO implements Serializable {
    // Id
    private Integer teacherId;
    // 姓名
    private String teacherName;
    // 工号
    private String teacherNum;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

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

    @Override
    public String toString() {
        return "TeacherInfoBO{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherNum='" + teacherNum + '\'' +
                '}';
    }

    public static final class TeacherInfoBOBuilder {
        // Id
        private Integer teacherId;
        // 姓名
        private String teacherName;
        // 工号
        private String teacherNum;

        private TeacherInfoBOBuilder() {
        }

        public static TeacherInfoBOBuilder aTeacherInfoBO() {
            return new TeacherInfoBOBuilder();
        }

        public TeacherInfoBOBuilder withTeacherId(Integer teacherId) {
            this.teacherId = teacherId;
            return this;
        }

        public TeacherInfoBOBuilder withTeacherName(String teacherName) {
            this.teacherName = teacherName;
            return this;
        }

        public TeacherInfoBOBuilder withTeacherNum(String teacherNum) {
            this.teacherNum = teacherNum;
            return this;
        }

        public TeacherInfoBO build() {
            TeacherInfoBO teacherInfoBO = new TeacherInfoBO();
            teacherInfoBO.setTeacherId(teacherId);
            teacherInfoBO.setTeacherName(teacherName);
            teacherInfoBO.setTeacherNum(teacherNum);
            return teacherInfoBO;
        }
    }
}
