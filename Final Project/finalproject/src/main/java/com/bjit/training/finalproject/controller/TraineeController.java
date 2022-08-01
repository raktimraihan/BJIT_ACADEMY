package com.bjit.training.finalproject.controller;

import com.bjit.training.finalproject.model.*;
import com.bjit.training.finalproject.payload.*;
import com.bjit.training.finalproject.payload.response.CommonResponse;
import com.bjit.training.finalproject.payload.response.TraineeDataResp;
import com.bjit.training.finalproject.repo.*;
import com.bjit.training.finalproject.security.WebSecurityConfig;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/trainee/*")
@Transactional
public class TraineeController {
    @Autowired
    TraineeProfilesrepo traineeProfilesrepo;

    @Autowired
    CredentialsRepo credentialsRepo;
    @Autowired
    CourseListRepo courseListRepo;
    @Autowired
    BatchesRepo batchesRepo;
    @Autowired
    EntityManager entityManager;
    @Autowired
    TrainerProfileRepo trainerProfileRepo;
    @Autowired
    QuizMarksConnectingRepo quizMarksConnectingRepo;
    PasswordEncoder passwordEncoder = new WebSecurityConfig().getPasswordEncoder();

    @PostMapping("/trainee-register")
    @CrossOrigin("*")
    public ResponseEntity<?> registerTrainee(@RequestBody TraineeRegisterReq traineeRegisterReq) {
        Optional<Credentials> credentials = credentialsRepo.findByUserName(traineeRegisterReq.getUserName()).stream().findFirst();
        ArrayList<String> emailList = new ArrayList<>();
        traineeProfilesrepo.findAll().forEach(e -> emailList.add(e.getEmail()));

        ArrayList<String> phoneList = new ArrayList<>();
        traineeProfilesrepo.findAll().forEach(e -> phoneList.add(e.getPhone()));

        if(credentials.isPresent()){
            if ((credentials.get().getUserName().equalsIgnoreCase(traineeRegisterReq.getUserName()))) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("User Name Already Taken", "Can't Insert"));
            }
        }

        if(emailList.contains(traineeRegisterReq.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("Email Already Taken", "Can't Insert"));
        }

        if(phoneList.contains(traineeRegisterReq.getPhone())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("Phone Already Taken", "Can't Insert"));
        }

        Credentials credentials1 = new Credentials(traineeRegisterReq.getUserName(), passwordEncoder.encode(traineeRegisterReq.getPassword()), traineeRegisterReq.getRole());
        Optional<Batches> batch = batchesRepo.findByBatchName(traineeRegisterReq.getBatch()).stream().findFirst();
        TraineeProfile traineeProfile01 = new TraineeProfile(credentials1.getUserName(), traineeRegisterReq.getName(),
                traineeRegisterReq.getAddress(), traineeRegisterReq.getPhone(), traineeRegisterReq.getEmail(),
                credentials1, batch.get());
        traineeProfilesrepo.save(traineeProfile01);

        return ResponseEntity.ok(new CommonResponse("Trainee Created Successfully.", ""));
    }

    @CrossOrigin("*")
    @GetMapping("/get-all-trainee-data")
    public ResponseEntity<?> getAllTraineeData(){
        final String[] name = new String[1];
        ArrayList<TraineeDataResp> arrayList = new ArrayList<>();

        traineeProfilesrepo.findAll().forEach(e->{
            Optional<Credentials> credentials = credentialsRepo.findByUserName(e.getUserName());
            ArrayList<String> assignedCourseList = new ArrayList<>();
            ArrayList<String> assignedQuizzesList = new ArrayList<>();
            ArrayList<String> reviewedQuizzesList = new ArrayList<>();

            courseListRepo.findAll().forEach(f->{
                f.getBatchCourseConnecting().forEach(g->{
                    if(g.getBatches().getBatchName().equalsIgnoreCase(e.getBatches().getBatchName())){
                        assignedCourseList.add(f.getCourseName());
                    }
                });
            });

            e.getQuizMarksConnecting().forEach(g->{
                if(g.getSubmitted()){
                    reviewedQuizzesList.add(g.getQuizzes().getTitle()+" Marks:"+g.getAttainedMarks()+" Reviewer: "+g.getReviewer());
                }
                else{
                    assignedQuizzesList.add(g.getQuizzes().getTitle());
                }
            });

            TraineeDataResp trainerDataResponse = new TraineeDataResp();
            trainerDataResponse.setUserName(e.getUserName());
            trainerDataResponse.setRole(credentials.get().getRole());
            trainerDataResponse.setName(e.getName());
            trainerDataResponse.setAddress(e.getAddress());
            trainerDataResponse.setPhone(e.getPhone());
            trainerDataResponse.setEmail(e.getEmail());
            trainerDataResponse.setBatchName(e.getBatches().getBatchName());
            trainerDataResponse.setPassword(credentials.get().getPassword());
            trainerDataResponse.setAssignedCourse(assignedCourseList.toString());
            trainerDataResponse.setReviewedQuiz(reviewedQuizzesList.toString());
            trainerDataResponse.setAssignedQuiz(assignedQuizzesList.toString());

            arrayList.add(trainerDataResponse);
            System.out.println(arrayList.toString());
        });
        return ResponseEntity.ok(arrayList);
    }

    @CrossOrigin("*")
    @PostMapping("/update-trainee")
    public ResponseEntity<?> getTrainerUpdate(@RequestBody TrainerUpdateReq trainerupdateReq){
        Optional<Credentials> credentials = credentialsRepo.findByUserName(trainerupdateReq.getUserName()).stream().findFirst();
        Optional<TraineeProfile> traineeProfile = traineeProfilesrepo.findByUserName(trainerupdateReq.getUserName()).stream().findFirst();

        if(credentials.isPresent() && traineeProfile.isPresent()){
            credentials.get().setPassword(passwordEncoder.encode(trainerupdateReq.getPassword()));
            credentialsRepo.save(credentials.get());

            traineeProfile.get().setName(trainerupdateReq.getName());
            traineeProfile.get().setAddress(trainerupdateReq.getAddress());
            traineeProfilesrepo.save(traineeProfile.get());

            return ResponseEntity.ok(new CommonResponse(trainerupdateReq.getUserName()+" Update Successfully",""));
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("User Not Found",""));

    }

    @CrossOrigin("*")
    @PostMapping("/trainee-delete")
    @Transactional
    public ResponseEntity<?> getTraineeDeleteInfo(@RequestBody TrainerDeleteReq trainerDeleteReq) {
        ArrayList<TraineeProfile> trainerProfiles = new ArrayList<>();

        traineeProfilesrepo.findAll().forEach(e->{
            if(e.getUserName().equals(trainerDeleteReq.getUserName())){
                e.setQuizMarksConnectingList(new HashSet<>());
                e.setBatches(new Batches());
                trainerProfiles.add(e);
                return;
            }
        });

        traineeProfilesrepo.saveAll(trainerProfiles);
        entityManager.flush();
        entityManager.clear();

        Optional<TraineeProfile> traineeProfile = traineeProfilesrepo.findByUserName(trainerDeleteReq.getUserName()).stream().findFirst();
        traineeProfilesrepo.delete(traineeProfile.get());
        entityManager.flush();
        entityManager.clear();

        return ResponseEntity.ok(new CommonResponse("User Deleted", ""));
    }

    @CrossOrigin("*")
    @PostMapping("/assign-new-batch")
    @Transactional
    public ResponseEntity<?> assignTraineeToNewBatch(@RequestBody AssignNewCourseTraineeReq assignNewCourseTraineeReq) {
        ArrayList<TraineeProfile> trainerProfiles = new ArrayList<>();

        traineeProfilesrepo.findAll().forEach(e->{
            if(e.getUserName().equals(assignNewCourseTraineeReq.getUserName())){
                Optional<Batches> batches = batchesRepo.findByBatchName(assignNewCourseTraineeReq.getCourseList());
                e.setBatches(batches.get());
                traineeProfilesrepo.save(e);
                return ;
            }
        });

        return ResponseEntity.ok(new CommonResponse("User Info Updated", ""));
    }

    @CrossOrigin("*")
    @PostMapping("/trainee-schedule")
    public ResponseEntity<?> getTraineeSchedule(@RequestBody TrainerDeleteReq trainerDeleteReq){
        ArrayList<TraineeRoutinResp> arrayList = new ArrayList<>();

        batchesRepo.findAll().forEach(e->{
            Optional<TraineeProfile> traineeProfile = traineeProfilesrepo.findByUserName(trainerDeleteReq.getUserName());
            if(traineeProfile.isPresent()){
                e.getBatchCourseConnecting().forEach(f->{
                    if(traineeProfile.get().getBatches().getBatchName().equalsIgnoreCase(e.getBatchName())){

                        TraineeRoutinResp trainerRoutinResp = new TraineeRoutinResp();
                        trainerRoutinResp.setBatchName(f.getBatches().getBatchName());
                        trainerRoutinResp.setCourseName(f.getCourseList().getCourseName());
                        trainerRoutinResp.setStartTime(f.getStartTime());
                        trainerRoutinResp.setEndTime(f.getEndTime());
                        String name = "";

                        Optional<TrainerProfile> trainerProfile = trainerProfileRepo.findByUserName(f.getTrainerName()).stream().findFirst();
                        if(trainerProfile.isPresent()){
                            name = trainerProfile.get().getName();
                        }
                        trainerRoutinResp.setTrainerName(name);

                        arrayList.add(trainerRoutinResp);
                    }
                });
            }
        });
        return ResponseEntity.ok(arrayList);
    }
}
