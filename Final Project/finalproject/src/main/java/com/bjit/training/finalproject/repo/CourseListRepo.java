package com.bjit.training.finalproject.repo;

import com.bjit.training.finalproject.model.CourseList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CourseListRepo extends CrudRepository<CourseList, Integer> {
    Optional<CourseList> findByCourseName(String courseName);
}
