package com.bjit.training.finalproject.repo;

import com.bjit.training.finalproject.model.QuizMarksConnecting;
import com.bjit.training.finalproject.model.TraineeProfile;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface QuizMarksConnectingRepo extends CrudRepository<QuizMarksConnecting, Integer> {
}
