package com.bjit.training.finalproject.payload;

import lombok.*;
import org.springframework.data.repository.NoRepositoryBean;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TrainerUpdateReq {
    String userName;
    String name;
    String password;
    String address;
}
