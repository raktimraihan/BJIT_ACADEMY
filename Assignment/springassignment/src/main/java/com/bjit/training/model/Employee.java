package com.bjit.training.model;

import java.util.Date;

import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(unique = true)
	@NotBlank(message = "User Name is mandatory")
	private String userName;
	@NotBlank(message = "Name is mandatory")
	private String name;
	private String address;
	private String dateOfJoining;
	@NotBlank(message = "Password is mandatory")
	private String password;
	@NotBlank(message = "Name is mandatory")
	private String role;

	public Employee(String userName, String name, String address, String dateOfJoining, String password, String role) {
		super();
		this.userName = userName;
		this.name = name;
		this.address = address;
		this.dateOfJoining = dateOfJoining;
		this.password = password;
		this.role = role;
	}

	public Employee() {
		super();
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

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", userName=" + userName + ", name=" + name + ", address=" + address
				+ ", dateOfJoining=" + dateOfJoining + ", password=" + password + ", role=" + role + "]";
	}

}
