package com.bjit.training.finalproject.payload;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BatchConnectingReq {
    String courseName;
    String batchName;
    String startHour;
    String endHour;
}
