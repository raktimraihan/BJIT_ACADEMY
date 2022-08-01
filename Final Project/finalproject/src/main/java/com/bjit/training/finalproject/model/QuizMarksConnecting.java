package com.bjit.training.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Transactional
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class QuizMarksConnecting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonIgnore
    private TraineeProfile traineeProfile;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonIgnore
    private Quizzes quizzes;
    private Double attainedMarks = 0.0;

    private String reviewer="";

    private Boolean submitted = false;

    private Boolean reviewed = false;

    public QuizMarksConnecting(TraineeProfile traineeProfile, Quizzes quizzes, Double attainedMarks) {
        this.traineeProfile = traineeProfile;
        this.quizzes = quizzes;
        this.attainedMarks = attainedMarks;
    }

    public QuizMarksConnecting(TraineeProfile traineeProfile, Quizzes quizzes) {
        this.traineeProfile = traineeProfile;
        this.quizzes = quizzes;
    }
}
