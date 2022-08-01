package com.example.springboottest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.resource.beans.internal.FallbackBeanInstanceProducer;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
  
    @Column(name = "name", nullable = false)
    private String name;
   
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name="classroll", nullable = false)
    private String classRoll;
 
    public Student() {
        
    }
    public Student(String name, String description, String classRoll) {
        super();
        this.name = name;
        this.description = description;
        this.classRoll = classRoll;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
	public String getClassRoll() {
		return classRoll;
	}
	public void setClassRoll(String classRoll) {
		this.classRoll = classRoll;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", description=" + description + ", classRoll=" + classRoll
				+ "]";
	}
}
