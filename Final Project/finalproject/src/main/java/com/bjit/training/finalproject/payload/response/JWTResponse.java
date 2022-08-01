package com.bjit.training.finalproject.payload.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class JWTResponse {
    private String accessToken;
    private String type = "Bearer";
    private List<String> roles;
    private String userName;
}
