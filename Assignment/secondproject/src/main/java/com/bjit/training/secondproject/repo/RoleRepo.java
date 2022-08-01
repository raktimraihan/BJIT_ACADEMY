package com.bjit.training.secondproject.repo;

import com.bjit.training.secondproject.model.RoleOfStudent;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepo extends CrudRepository<RoleOfStudent, Integer> {
    Optional<RoleOfStudent> findByUserName(String userName);
}
