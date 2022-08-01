package com.bjit.training.finalproject.payload.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class QuizAllDataResp {
    String title;
    String batchName;
    String traineeUserName;
    String traineeName;
    Double totalMarks;
    Double attainedMarks;
    String reviewerUserName;
    String reviewerName;
}
