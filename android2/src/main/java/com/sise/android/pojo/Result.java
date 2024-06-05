package com.sise.android.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors
public class Result {
    String id;
    String cid;
    String courseName;
    String courseCode;
    String score;
}
