package com.bjit.training.finalproject.repo;

import com.bjit.training.finalproject.model.Batches;
import com.bjit.training.finalproject.model.TraineeProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface TraineeProfilesrepo extends CrudRepository<TraineeProfile, Integer> {
    Optional<TraineeProfile> findByUserName(String userName);

    @Transactional
    void removeByUserName(String userName);

    @Transactional
    void delete(TraineeProfile traineeProfile);

    Optional<TraineeProfile> findByBatches(Batches batches);
}
