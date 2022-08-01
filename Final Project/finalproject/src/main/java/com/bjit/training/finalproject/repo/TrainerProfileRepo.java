package com.bjit.training.finalproject.repo;

import com.bjit.training.finalproject.model.TraineeProfile;
import com.bjit.training.finalproject.model.TrainerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerProfileRepo extends JpaRepository<TrainerProfile, Integer> {
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    Optional<TrainerProfile> findByUserName(String userName);

    @Transactional
    void delete(TrainerProfile trainerProfile);

    @Transactional
    Long removeByUserName(String userName);


}
