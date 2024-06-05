package com.sise.android.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors
public class Classed {
    private String id;
    private String className;
    private String teacher;
    private String counselor;
}
