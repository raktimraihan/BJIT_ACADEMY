package com.bjit.training.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Transactional
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainerProfile {
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "cnr_tnr_crselst",
            joinColumns = @JoinColumn(name = "t_uname"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnore
    Set<CourseList> assignedCourses = new HashSet<>();

    @Id
    @Column(length = 12)
    private String userName;
    private String name;
    private String address;
    private String phone;
    private String email;
    private double startHour;
    private double endHour;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credentials_c_id")
    private Credentials credentials;

    public TrainerProfile(CourseList assignedCourses, String userName, String name, String address,
                          String phone, String email, double startHour, double endHour,
                          Credentials credentials) {
        this.assignedCourses.add(assignedCourses);
        this.userName = userName;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.startHour = startHour;
        this.endHour = endHour;
        this.credentials = credentials;
    }

    public TrainerProfile(CourseList assignedCourses, String userName, String name, String address,
                          String phone, String email,
                          Credentials credentials) {
        this.assignedCourses.add(assignedCourses);
        this.userName = userName;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.credentials = credentials;

    }
}
