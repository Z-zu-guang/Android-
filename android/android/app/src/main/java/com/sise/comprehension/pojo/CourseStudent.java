package com.sise.comprehension.pojo;

/**
 * @author draven
 */
public class CourseStudent {
    private String day;
    private String time;
    private String courseName;
    private String courseCode;
    private String instructor;
    private String classroom;
    private String week;

    @Override
    public String toString() {
        return "CourseStudent{" +
                "day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", instructor='" + instructor + '\'' +
                ", classroom='" + classroom + '\'' +
                ", week='" + week + '\'' +
                '}';
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public CourseStudent(String day, String time, String courseName, String courseCode, String instructor, String classroom, String week) {
        this.day = day;
        this.time = time;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.instructor = instructor;
        this.classroom = classroom;
        this.week = week;
    }

    public CourseStudent() {
    }
}
