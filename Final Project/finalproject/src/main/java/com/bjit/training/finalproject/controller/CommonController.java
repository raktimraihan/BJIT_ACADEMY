package com.bjit.training.finalproject.controller;

import com.bjit.training.finalproject.model.TraineeProfile;
import com.bjit.training.finalproject.model.TrainerProfile;
import com.bjit.training.finalproject.payload.TrainerDeleteReq;
import com.bjit.training.finalproject.payload.response.CommonResponse;
import com.bjit.training.finalproject.payload.response.GetUserDetailsResp;
import com.bjit.training.finalproject.payload.response.JWTResponse;
import com.bjit.training.finalproject.payload.response.LoginRequest;
import com.bjit.training.finalproject.repo.TraineeProfilesrepo;
import com.bjit.training.finalproject.repo.TrainerProfileRepo;
import com.bjit.training.finalproject.security.JwtUtils;
import com.bjit.training.finalproject.security.UserDetaislImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class CommonController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    TraineeProfilesrepo traineeProfilesrepo;
    @Autowired
    TrainerProfileRepo trainerProfileRepo;

    @CrossOrigin("*")
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        UserDetaislImpl userDetaisl = (UserDetaislImpl) authentication.getPrincipal();
        List<String> roles = userDetaisl.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(new JWTResponse(token,"Bearer", roles, userDetaisl.getUsername()));
    }

    @CrossOrigin("*")
    @PostMapping("/get-user-name")
    public ResponseEntity<?> getUserName(@RequestBody TrainerDeleteReq trainerDeleteReq){
        Optional<TraineeProfile> traineeProfile = traineeProfilesrepo.findByUserName(trainerDeleteReq.getUserName()).stream().findFirst();
        Optional<TrainerProfile> trainerProfile = trainerProfileRepo.findByUserName(trainerDeleteReq.getUserName()).stream().findFirst();

        if(traineeProfile.isPresent()){
            return ResponseEntity.ok(new GetUserDetailsResp(traineeProfile.get().getName(),
                    trainerProfile.get().getPhone(), trainerProfile.get().getAddress(), trainerProfile.get().getEmail()));
        }

        if(trainerProfile.isPresent()){
            return ResponseEntity.ok(new GetUserDetailsResp(trainerProfile.get().getName(), trainerProfile.get().getPhone(),
                    trainerProfile.get().getAddress(), trainerProfile.get().getEmail()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("User Not Found",""));
    }
}
