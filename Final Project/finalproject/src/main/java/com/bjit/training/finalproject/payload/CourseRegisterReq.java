package com.bjit.training.finalproject.payload;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class CourseRegisterReq {

    private String courseName;
    private String topic;
    private String description;
}
