package com.bjit.training.finalproject.payload;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TraineeRoutinResp {
    String batchName;
    String courseName;
    Double startTime;
    Double endTime;
    String trainerName;
}
