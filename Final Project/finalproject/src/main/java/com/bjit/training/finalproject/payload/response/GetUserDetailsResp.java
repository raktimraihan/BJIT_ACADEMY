package com.bjit.training.finalproject.payload.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetUserDetailsResp {
    String name;
    String phone;
    String address;
    String email;
}
