package com.sise.android.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author draven
 */
@Accessors
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CourseStudent {
    private String day;
    private String time;
    private String courseName;
    private String courseCode;
    private String instructor;
    private String classroom;
    private String week;

}
