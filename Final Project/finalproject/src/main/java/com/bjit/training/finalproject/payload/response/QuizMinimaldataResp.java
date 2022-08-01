package com.bjit.training.finalproject.payload.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class QuizMinimaldataResp {
    String title;
    String batchName;
    String traineeUserName;
    String traineeName;
    String totalMarks;
}
