package com.bjit.training.secondproject.repo;

import com.bjit.training.secondproject.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepo extends CrudRepository<Student, Integer> {
    @Query(value = "SELECT student.id, address, name from student LIMIT 1", nativeQuery = true)
    Optional<Student> getCustom();

}
