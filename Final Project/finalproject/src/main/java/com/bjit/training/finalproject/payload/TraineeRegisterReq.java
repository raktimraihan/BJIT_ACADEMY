package com.bjit.training.finalproject.payload;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TraineeRegisterReq {

    String userName;
    String name;
    String email;
    String address;
    String phone;
    String password;
    String role;
    String batch;
}
