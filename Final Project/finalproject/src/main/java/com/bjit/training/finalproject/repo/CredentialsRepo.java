package com.bjit.training.finalproject.repo;

import com.bjit.training.finalproject.model.Credentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CredentialsRepo extends CrudRepository<Credentials, Integer> {
    Optional<Credentials> findByUserName(String userName);
}
