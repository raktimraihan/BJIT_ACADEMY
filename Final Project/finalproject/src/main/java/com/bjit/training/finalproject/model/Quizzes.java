package com.bjit.training.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Entity
@Transactional
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Quizzes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Double totalMarks;
    private String title;
    private long durationInMills;
    private Boolean active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(
            name="cnr_batch_quiz",
            joinColumns = @JoinColumn(name="quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "batch_id")
    )
    Set<Batches> batchesSet = new HashSet<>();

    @OneToMany(mappedBy = "quizzes", cascade = CascadeType.ALL)
    @JsonIgnore
    Set<QuizMarksConnecting> quizMarksConnecting = new HashSet<>();

    public Quizzes(Double totalMarks, String title, long durationInMills, Boolean active) {
        this.totalMarks = totalMarks;
        this.title = title;
        this.durationInMills = durationInMills;
        this.active = active;
    }

    public void setQuizMarksConnecting(QuizMarksConnecting quizMarksConnecting) {
        this.quizMarksConnecting.add(quizMarksConnecting);
    }

    public void setBatchesSet(Batches batchesSet) {
        this.batchesSet.add(batchesSet);
    }
}
