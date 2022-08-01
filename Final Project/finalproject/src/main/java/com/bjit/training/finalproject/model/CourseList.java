package com.bjit.training.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Entity
@Transactional
@Getter
@Setter
@NoArgsConstructor

public class CourseList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int courseId;
    private String courseName;
    private String topic;
    @Column(length = 1200)
    private String description;

    public CourseList(String courseName, String topic, String description) {
        this.courseName = courseName;
        this.topic = topic;
        this.description = description;
    }

    @ManyToMany(mappedBy = "assignedCourses", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    Set<TrainerProfile> trainerProfiles = new HashSet<>();

    @OneToMany(mappedBy = "courseList", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    Set<BatchCourseConnecting> batchCourseConnecting = new HashSet<>();
}
