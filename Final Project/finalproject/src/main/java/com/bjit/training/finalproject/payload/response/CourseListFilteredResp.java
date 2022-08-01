package com.bjit.training.finalproject.payload.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class CourseListFilteredResp {
    String batchName;
    Double startTime;
    Double endTime;
    String courseName;
}
