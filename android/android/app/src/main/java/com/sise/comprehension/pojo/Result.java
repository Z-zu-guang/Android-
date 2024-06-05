package com.sise.comprehension.pojo;


public class Result {
    String id;
    String courseName;
    String courseCode;
    String score;

    @Override
    public String toString() {
        return "Result{" +
                "id='" + id + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Result() {
    }

    public Result(String id, String courseName, String courseCode, String score) {
        this.id = id;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.score = score;
    }
}
