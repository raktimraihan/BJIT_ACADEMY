package com.bjit.training.secondproject.security;

import com.bjit.training.secondproject.model.RoleOfStudent;
import com.bjit.training.secondproject.repo.RoleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class UserDetailsServImpl implements UserDetailsService {

    @Autowired
    RoleRepo roleRepo;
    Logger logger = LoggerFactory.getLogger("UserDetailsServImpl.class");
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try{
            Optional<RoleOfStudent> roleOfStudent = roleRepo.findByUserName(userName);
            logger.info("Logged user details: "+roleOfStudent.toString());
            return roleOfStudent.map(UserDetailsImpl::new).get();
        }
        catch (UsernameNotFoundException ex){
            logger.info("Logged user details: "+ex.toString());
        }
        return null;
    }
}
