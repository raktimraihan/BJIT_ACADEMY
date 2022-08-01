package com.bjit.training.finalproject.repo;

import com.bjit.training.finalproject.model.Batches;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.MediaSize;
import java.util.Optional;

@Repository
@Transactional
public interface BatchesRepo extends CrudRepository<Batches, Integer> {
    Optional<Batches> findByBatchName(String batchName);
}
