package com.bjit.training.finalproject.payload;

import lombok.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
@AllArgsConstructor
public class TrainerRoutinResp {
    String batchName;
    String courseName;
    Double startTime;
    Double endTime;
}
