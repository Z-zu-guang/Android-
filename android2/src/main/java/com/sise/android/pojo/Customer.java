package com.sise.android.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author draven
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors
public class Customer {

  private String id;
  private String name;
  private String no;
  private String password;
  private String sex;
  private String role;
  private String avatar;
  private String phone;
  private Date birthday;
  private Date enrollmentDate;
  private Classed classed;
  private String classId;
  private String className;
  private String teacher;
  private String counselor;
}
