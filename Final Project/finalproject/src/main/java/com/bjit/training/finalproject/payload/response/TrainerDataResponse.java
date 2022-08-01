package com.bjit.training.finalproject.payload.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrainerDataResponse {

    String userName;
    String role;
    String Name;
    String address;
    String phone;
    String email;
    String password;
    String assignedCourse;
    String reviewedQuiz;

}
