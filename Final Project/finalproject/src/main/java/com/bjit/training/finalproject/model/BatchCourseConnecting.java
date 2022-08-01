package com.bjit.training.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Transactional
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchCourseConnecting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonIgnore
    private Batches batches;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonIgnore
    private CourseList courseList;
    private Double startTime;
    private Double endTime;

    private Boolean assigned = false;

    private String trainerName="";

    public BatchCourseConnecting(Batches batches, CourseList courseList, Double startTime, Double endTime) {
        this.batches = batches;
        this.courseList = courseList;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
