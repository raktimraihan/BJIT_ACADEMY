package com.bjit.training.finalproject.repo;

import com.bjit.training.finalproject.model.Quizzes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface QuizzesRepo extends CrudRepository<Quizzes, Integer> {
    Optional<Quizzes> findByTitle(String title);
}
