package com.my.workmanagement.model.bo;

import java.io.Serializable;

public class TeacherInfoBO implements Serializable {
    //id
    private Integer teacherId;
    // 姓名
    private String teacherName;
    // 工号
    private String teacherNum;
    //教师电话
    private String teacherTell;

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

    public String getTeacherTell() {
        return teacherTell;
    }

    public void setTeacherTell(String teacherTell) {
        this.teacherTell = teacherTell;
    }


    public static final class TeacherInfoBOBuilder {
        //id
        private Integer teacherId;
        // 姓名
        private String teacherName;
        // 工号
        private String teacherNum;
        //教师电话
        private String teacherTell;

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

        public TeacherInfoBOBuilder withTeacherTell(String teacherTell) {
            this.teacherTell = teacherTell;
            return this;
        }

        public TeacherInfoBO build() {
            TeacherInfoBO teacherInfoBO = new TeacherInfoBO();
            teacherInfoBO.setTeacherId(teacherId);
            teacherInfoBO.setTeacherName(teacherName);
            teacherInfoBO.setTeacherNum(teacherNum);
            teacherInfoBO.setTeacherTell(teacherTell);
            return teacherInfoBO;
        }
    }

    @Override
    public String toString() {
        return "TeacherInfoBO{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherNum='" + teacherNum + '\'' +
                ", teacherTell='" + teacherTell + '\'' +
                '}';
    }
}
