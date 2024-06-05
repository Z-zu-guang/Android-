package com.sise.comprehension.pojo;

public class Classed {
    private String id;
    private String className;
    private String teacher;
    private String counselor;

    public Classed(String id, String className, String teacher, String counselor) {
        this.id = id;
        this.className = className;
        this.teacher = teacher;
        this.counselor = counselor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }

    public Classed() {
    }

    @Override
    public String toString() {
        return "Classed{" +
                "id='" + id + '\'' +
                ", className='" + className + '\'' +
                ", teacher='" + teacher + '\'' +
                ", counselor='" + counselor + '\'' +
                '}';
    }
}
