package com.bjit.training.secondproject.model;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Transactional
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String address;
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_role_id")
    private RoleOfStudent roleOfStudent;

    public Student(String name, String address, int age, RoleOfStudent roleOfStudent) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.roleOfStudent = roleOfStudent;
    }

    public Student() {
    }

    public RoleOfStudent getRoleOfStudent() {
        return roleOfStudent;
    }

    public void setRoleOfStudent(RoleOfStudent roleOfStudent) {
        this.roleOfStudent = roleOfStudent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name='" + name + '\'' + ", address='" + address + '\'' + ", age=" + age + ", roleOfStudent=" + roleOfStudent + '}';
    }
}
