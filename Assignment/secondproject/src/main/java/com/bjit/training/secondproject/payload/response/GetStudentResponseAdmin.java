package com.bjit.training.secondproject.payload.response;

public class GetStudentResponseAdmin {

    private int id;
    private String userName;
    private String name;
    private String address;
    private int age;
    private String role;

    public GetStudentResponseAdmin(int id, String userName, String name, String address, int age, String role) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.address = address;
        this.age = age;
        this.role = role;
    }

    public GetStudentResponseAdmin() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
