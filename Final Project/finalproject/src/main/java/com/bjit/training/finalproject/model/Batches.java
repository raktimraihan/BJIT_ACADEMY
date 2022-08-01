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
@Getter
@Setter
@AllArgsConstructor
public class Batches {

    @OneToMany(mappedBy = "batches", cascade = CascadeType.ALL)
    @JsonIgnore
    Set<BatchCourseConnecting> batchCourseConnecting = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int batchID;
    @Column(nullable = true)
    private String batchName;
    private Boolean active;

    @ManyToMany(mappedBy = "batchesSet", cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Quizzes> quizzesSet = new HashSet<>();

    public Batches(String batchName, Boolean active) {
        this.batchName = batchName;
        this.active = active;
    }

    public void setBatchCourseConnecting(BatchCourseConnecting batchCourseConnecting) {
        this.batchCourseConnecting.add(batchCourseConnecting);
    }

    public void setBatchCourseConnecting(Set<BatchCourseConnecting> batchCourseConnecting) {
        this.batchCourseConnecting = batchCourseConnecting;
    }

}
