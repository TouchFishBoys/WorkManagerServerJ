package com.my.workmanagement.entity;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "student_info")
public class StudentDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stuId;
    @Column(nullable = false, unique = true)
    private String stuNum;
    @Column(nullable = false)
    private String stuName;
    @Column(nullable = false)
    private String stuPassword;
    @Column(nullable = false)
    private String stuClass;
    @CreationTimestamp
    private Timestamp gmtCreate;
    @CreationTimestamp
    private Timestamp gmtModified;

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "StudentDO{" +
                "stuId=" + stuId +
                ", stuNum='" + stuNum + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuPassword='" + stuPassword + '\'' +
                ", stuClass='" + stuClass + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }


    public static final class StudentBuilder {
        private int stuId;
        private String stuNum;
        private String stuName;
        private String stuPassword;
        private String stuClass;
        private Timestamp gmtCreate;
        private Timestamp gmtModified;

        private StudentBuilder() {
        }

        public static StudentBuilder aStudentDO() {
            return new StudentBuilder();
        }

        public StudentBuilder withStuId(int stuId) {
            this.stuId = stuId;
            return this;
        }

        public StudentBuilder withStuNum(String stuNum) {
            this.stuNum = stuNum;
            return this;
        }

        public StudentBuilder withStuName(String stuName) {
            this.stuName = stuName;
            return this;
        }

        public StudentBuilder withStuPassword(String stuPassword) {
            this.stuPassword = stuPassword;
            return this;
        }

        public StudentBuilder withStuClass(String stuClass) {
            this.stuClass = stuClass;
            return this;
        }

        public StudentBuilder withGmtCreate(Timestamp gmtCreate) {
            this.gmtCreate = gmtCreate;
            return this;
        }

        public StudentBuilder withGmtModified(Timestamp gmtModified) {
            this.gmtModified = gmtModified;
            return this;
        }

        public StudentDO build() {
            StudentDO studentDO = new StudentDO();
            studentDO.setStuId(stuId);
            studentDO.setStuNum(stuNum);
            studentDO.setStuName(stuName);
            studentDO.setStuPassword(stuPassword);
            studentDO.setStuClass(stuClass);
            studentDO.setGmtCreate(gmtCreate);
            studentDO.setGmtModified(gmtModified);
            return studentDO;
        }
    }
}
