package com.bjit.training.finalproject.payload.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TraineeDataResp {
    String userName;
    String role;
    String Name;
    String address;
    String phone;
    String email;
    String password;
    String batchName;
    String assignedCourse;
    String assignedQuiz;
    String reviewedQuiz;
}
