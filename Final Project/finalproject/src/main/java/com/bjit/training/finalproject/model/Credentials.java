package com.bjit.training.finalproject.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Transactional
@Getter
@Setter
@NoArgsConstructor

public class Credentials {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int c_id;
    @Column(unique = true, nullable = false)
    @NonNull
    private String userName;
    @Column(nullable = false)
    @NonNull
    private String password;
    @NonNull
    private String role;
    public Credentials(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
}
