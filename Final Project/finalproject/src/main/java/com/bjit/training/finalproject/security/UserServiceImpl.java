package com.bjit.training.finalproject.security;

import com.bjit.training.finalproject.model.Credentials;
import com.bjit.training.finalproject.repo.CredentialsRepo;
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
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    CredentialsRepo credentialsRepo;
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            Optional<Credentials> credentials = credentialsRepo.findByUserName(username);
            logger.info("Found User info: "+credentials.toString());
            return credentials.map(UserDetaislImpl::new).get();
        } catch (UsernameNotFoundException e) {
            logger.error("Not Found User info: "+credentialsRepo.findByUserName(username).toString());
        }
        return null;
    }
}
