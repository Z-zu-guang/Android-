package com.sise.comprehension.pojo;




public class Info {
    private String code;
    private Object pojo;

    public Info() {
    }

    public Info(String code, Object pojo) {
        this.code = code;
        this.pojo = pojo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getPojo() {
        return pojo;
    }

    public void setPojo(Object pojo) {
        this.pojo = pojo;
    }

    @Override
    public String toString() {
        return "Info{" +
                "code='" + code + '\'' +
                ", object=" + pojo +
                '}';
    }
}
