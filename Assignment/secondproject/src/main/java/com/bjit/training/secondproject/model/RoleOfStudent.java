package com.bjit.training.secondproject.model;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Transactional
public class RoleOfStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String role;

    private String userName;

    private String password;

    public RoleOfStudent(String role, String password, String userName) {
        this.role = role;
        this.password = password;
        this.userName = userName;
    }

    public RoleOfStudent() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "RoleOfStudent{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
