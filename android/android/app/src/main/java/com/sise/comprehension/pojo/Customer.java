package com.sise.comprehension.pojo;


import java.util.Date;

/**
 * @author draven
 */

public class Customer {

  private String id;
  private String name;
  private String no;
  private String password;
  private String sex;
  private String role;
  private String avatar;
  private String phone;
  private String birthday;
  private String enrollmentDate;
  private Classed classed;

  public Classed getClassed() {
    return classed;
  }

  public void setClassed(Classed classed) {
    this.classed = classed;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getEnrollmentDate() {
    return enrollmentDate;
  }

  public void setEnrollmentDate(String enrollmentDate) {
    this.enrollmentDate = enrollmentDate;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }



  public Customer() {
  }

  public Customer(String id, String name, String no, String password, String sex, String role, String avatar, String phone, String birthday, String enrollmentDate, Classed classed) {
    this.id = id;
    this.name = name;
    this.no = no;
    this.password = password;
    this.sex = sex;
    this.role = role;
    this.avatar = avatar;
    this.phone = phone;
    this.birthday = birthday;
    this.enrollmentDate = enrollmentDate;
    this.classed = classed;
  }

  @Override
  public String toString() {
    return "Customer{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", no='" + no + '\'' +
            ", password='" + password + '\'' +
            ", sex='" + sex + '\'' +
            ", role='" + role + '\'' +
            ", avatar='" + avatar + '\'' +
            ", phone='" + phone + '\'' +
            ", birthday='" + birthday + '\'' +
            ", enrollmentDate='" + enrollmentDate + '\'' +
            ", classed=" + classed +
            '}';
  }
}
