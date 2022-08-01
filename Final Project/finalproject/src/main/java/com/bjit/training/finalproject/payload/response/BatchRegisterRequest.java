package com.bjit.training.finalproject.payload.response;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class BatchRegisterRequest {
    private String batchName;
    private boolean active;
}
