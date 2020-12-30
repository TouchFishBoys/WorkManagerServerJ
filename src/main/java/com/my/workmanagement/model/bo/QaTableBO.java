package com.my.workmanagement.model.bo;

import java.util.Date;
import java.util.List;

public class QaTableBO {
    private String courseName; // 课程名称
    private String productName; // 项目名称
    private String author; // 参与者
    private Date qaTime; // 答辩时间
    private Integer score;

    private List<QaTableItemBO> tables;

    public static class QaTableItemBO {
        private String question;
        private String answer;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        @Override
        public String toString() {
            return "QaTableItemBO{" +
                    "question='" + question + '\'' +
                    ", answer='" + answer + '\'' +
                    '}';
        }
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getQaTime() {
        return qaTime;
    }

    public void setQaTime(Date qaTime) {
        this.qaTime = qaTime;
    }

    public List<QaTableItemBO> getTables() {
        return tables;
    }

    public void setTables(List<QaTableItemBO> tables) {
        this.tables = tables;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "QaTableBO{" +
                "courseName='" + courseName + '\'' +
                ", productName='" + productName + '\'' +
                ", author='" + author + '\'' +
                ", qaTime=" + qaTime +
                ", tables=" + tables +
                '}';
    }


}
