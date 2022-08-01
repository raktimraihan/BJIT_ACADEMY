package com.bjit.training.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Entity
@Transactional
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TraineeProfile {

    @Id
    @Column(length = 12)
    private String userName;
    private String name;
    private String address;
    private String phone;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creden_id")
    private Credentials credentials;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="fk_batch_id")
    private  Batches batches;

    @OneToMany(mappedBy = "traineeProfile", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    Set<QuizMarksConnecting> quizMarksConnecting = new HashSet<>();

    public TraineeProfile(String userName, String name, String address, String phone, String email, Credentials credentials, Batches batches) {
        this.userName = userName;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.credentials = credentials;
        this.batches = batches;
    }

    public void setQuizMarksConnecting(QuizMarksConnecting quizMarksConnecting) {
        this.quizMarksConnecting.add(quizMarksConnecting);
    }

    public void setQuizMarksConnectingList(Set<QuizMarksConnecting> ez) {
        this.quizMarksConnecting = ez;
    }
}
