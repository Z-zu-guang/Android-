package com.sise.android.pojo;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors
@Builder
public class Results {
    private String id;
    private String sid;
    private String cid;
    private String score;
}
