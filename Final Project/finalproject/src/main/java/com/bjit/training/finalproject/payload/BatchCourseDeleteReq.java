package com.bjit.training.finalproject.payload;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BatchCourseDeleteReq {
    String batchName;
    String courseName;
}
