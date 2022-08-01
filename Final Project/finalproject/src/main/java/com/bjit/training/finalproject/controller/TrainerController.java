package com.bjit.training.finalproject.controller;

import com.bjit.training.finalproject.model.*;
import com.bjit.training.finalproject.payload.*;
import com.bjit.training.finalproject.payload.response.CommonResponse;
import com.bjit.training.finalproject.payload.response.TrainerDataResponse;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@RestController
@RequestMapping("/trainer/*")
@Transactional
public class TrainerController {
    @Autowired
    CredentialsRepo credentialsRepo;
    @Autowired
    TrainerProfileRepo trainerProfilers;
    @Autowired
    BatchesRepo batchesRepo;
    @Autowired
    CourseListRepo courseListRepo;
    @Autowired
    QuizzesRepo quizzesRepo;
    @Autowired
    EntityManager entityManager;
    PasswordEncoder passwordEncoder = new WebSecurityConfig().getPasswordEncoder();

    @CrossOrigin("*")
    @PostMapping("/trainer-registration")
    public ResponseEntity<?> registerTrainer(@RequestBody TrainerRegReq trainerRegReq) {
        System.out.println(trainerRegReq.toString());
        Optional<Credentials> credentials = credentialsRepo.findByUserName(trainerRegReq.getUserName()).stream().findFirst();
        ArrayList<String> emailList = new ArrayList<>();
        trainerProfilers.findAll().forEach(e -> emailList.add(e.getEmail()));

        ArrayList<String> phoneList = new ArrayList<>();
        trainerProfilers.findAll().forEach(e -> phoneList.add(e.getPhone()));

        if(credentials.isPresent()){
            if ((credentials.get().getUserName().equalsIgnoreCase(trainerRegReq.getUserName()))) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("User Name Already Taken", "Can't Insert"));
            }
        }

        if(emailList.contains(trainerRegReq.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("Email Already Taken", "Can't Insert"));
        }

        if(phoneList.contains(trainerRegReq.getPhone())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("Phone Already Taken", "Can't Insert"));
        }

        Credentials credentials1 = new Credentials(trainerRegReq.getUserName(), passwordEncoder.encode(trainerRegReq.getPassword()), trainerRegReq.getRole());
        String[] arrayOFCourseInfo = trainerRegReq.getCourse().split("-->");
        String courseName = arrayOFCourseInfo[0];
        String batchName = arrayOFCourseInfo[1];

        Optional<Batches> batch = batchesRepo.findByBatchName(batchName).stream().findFirst();
        Optional<CourseList> courseList = courseListRepo.findByCourseName(courseName).stream().findFirst();

        TrainerProfile traineeProfile = new TrainerProfile(courseList.get(), credentials1.getUserName(), trainerRegReq.getName(),
                trainerRegReq.getAddress(), trainerRegReq.getPhone(), trainerRegReq.getEmail(),credentials1);
        batch.get().getBatchCourseConnecting().forEach( e->{
            if(e.getCourseList().getCourseName().equalsIgnoreCase(courseName)){
                e.setAssigned(true);
                e.setTrainerName(trainerRegReq.getUserName());
            }
        });

        trainerProfilers.save(traineeProfile);
        return ResponseEntity.ok(new CommonResponse("Trainer "+trainerRegReq.getName()+" Created Successfully.", ""));
    }

    @CrossOrigin("*")
    @GetMapping("/get-all-trainer-data")
    public ResponseEntity<?> getAllTrainerData(){
        final String[] name = new String[1];
        ArrayList<TrainerDataResponse> arrayList = new ArrayList<>();

        trainerProfilers.findAll().forEach(e->{
            Optional<Credentials> credentials = credentialsRepo.findByUserName(e.getUserName());
            ArrayList<String> assignedCourseList = new ArrayList<>();
            ArrayList<String> quizzesList = new ArrayList<>();

            courseListRepo.findAll().forEach(f->{
                f.getBatchCourseConnecting().forEach(g ->{
                    if(g.getTrainerName().equalsIgnoreCase(e.getUserName())){
                        int count = 1;
                        assignedCourseList.add(g.getCourseList().getCourseName()+"--"+g.getBatches().getBatchName()+" From: "+
                        g.getStartTime()+" To= "+g.getEndTime());
                        System.out.println(f.getCourseName());
                    }
                });
            });

            quizzesRepo.findAll().forEach(h ->{
                h.getQuizMarksConnecting().forEach(i ->{
                    if(i.getReviewer().equalsIgnoreCase(e.getUserName())){
                        quizzesList.add(i.getQuizzes().getTitle());
                    }
                });
            });

            TrainerDataResponse trainerDataResponse = new TrainerDataResponse();
            trainerDataResponse.setUserName(credentials.get().getUserName());
            trainerDataResponse.setRole(credentials.get().getRole());
            trainerDataResponse.setName(e.getName());
            trainerDataResponse.setAddress(e.getAddress());
            trainerDataResponse.setPhone(e.getPhone());
            trainerDataResponse.setEmail(e.getEmail());
            trainerDataResponse.setAssignedCourse(assignedCourseList.toString());
            trainerDataResponse.setReviewedQuiz(quizzesList.toString());

            arrayList.add(trainerDataResponse);
        });
        return ResponseEntity.ok(arrayList);
    }

    @CrossOrigin("*")
    @PostMapping("/update-trainer")
    public ResponseEntity<?> getTrainerUpdate(@RequestBody TrainerUpdateReq trainerupdateReq){
        Optional<Credentials> credentials = credentialsRepo.findByUserName(trainerupdateReq.getUserName()).stream().findFirst();
        Optional<TrainerProfile> trainerProfile = trainerProfilers.findByUserName(trainerupdateReq.getUserName()).stream().findFirst();

        if(credentials.isPresent() && trainerProfile.isPresent()){
            credentials.get().setPassword(passwordEncoder.encode(trainerupdateReq.getPassword()));
            credentialsRepo.save(credentials.get());

            trainerProfile.get().setName(trainerupdateReq.getName());
            trainerProfile.get().setAddress(trainerupdateReq.getAddress());
            trainerProfilers.save(trainerProfile.get());

            return ResponseEntity.ok(new CommonResponse(trainerupdateReq.getUserName()+" Update Successfully",""));
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("User Not Found",""));

    }

    @CrossOrigin("*")
    @PostMapping("/trainer-delete")
    @Transactional
    public ResponseEntity<?> getTrainerDeleteInfo(@RequestBody TrainerDeleteReq trainerDeleteReq) {

        Optional<TrainerProfile> trainerProfile = trainerProfilers.findByUserName(trainerDeleteReq.getUserName()).stream().findFirst();
        ArrayList<TrainerProfile> trainerProfiles = new ArrayList<>();
        trainerProfilers.findAll().forEach(e -> {
            if (e.getUserName().equalsIgnoreCase(trainerDeleteReq.getUserName())) {
                e.setAssignedCourses(new HashSet<>());
                trainerProfiles.add(e);
                return;
            }
        });

        try {
            entityManager.flush();
            entityManager.clear();

            trainerProfilers.saveAll(trainerProfiles);
            entityManager.flush();
            entityManager.clear();

            Optional<TrainerProfile> trainerProfile1 = trainerProfilers.findByUserName(trainerDeleteReq.getUserName()).stream().findFirst();
            trainerProfilers.delete(trainerProfile1.get());
            entityManager.flush();
            entityManager.clear();

            ArrayList<Batches> batches = new ArrayList<>();
            batchesRepo.findAll().forEach(e ->{
                e.getBatchCourseConnecting().forEach(f->{
                    if(f.getTrainerName().equalsIgnoreCase(trainerDeleteReq.getUserName())){
                        f.setAssigned(false);
                        f.setTrainerName("");
                        batches.add(e);
                    }
                });
            });

            batchesRepo.saveAll(batches);
            entityManager.flush();
            entityManager.clear();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(new CommonResponse("User Deleted", ""));
    }

    @CrossOrigin("*")
    @PostMapping("/assign-new-course")
    public ResponseEntity<?> assignNewCourseToTrainee(@RequestBody AssignNewCourseTraineeReq assignTnr){
        String[] info = assignTnr.getCourseList().split(",");
        Optional<Batches> batches = batchesRepo.findByBatchName(info[0]).stream().findFirst();

        if(batches.isPresent()){
            ArrayList<Batches> batches1 = new ArrayList<>();
            batchesRepo.findAll().forEach(e->{
                e.getBatchCourseConnecting().forEach(f->{
                    if(f.getCourseList().getCourseName().equalsIgnoreCase(info[1]) && f.getBatches().getBatchName().equalsIgnoreCase(info[0])){
                        f.setTrainerName(assignTnr.getUserName());
                        f.setAssigned(true);
                        batches1.add(e);
                    }
                });
            });

            return ResponseEntity.ok(new CommonResponse(info[0]+" assigned to "+assignTnr.getUserName(),""));
        }
        System.out.println(info[0]+" Batch "+info[1]);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("Can't Assign! ERROR!",""));
    }

    @CrossOrigin("*")
    @PostMapping("/remove-new-course")
    public ResponseEntity<?> removeNewCourseToTrainee(@RequestBody AssignNewCourseTraineeReq assignTnr){
        String[] info = assignTnr.getCourseList().split(",");
        Optional<Batches> batches = batchesRepo.findByBatchName(info[0]).stream().findFirst();

        if(batches.isPresent()){
            ArrayList<Batches> batches1 = new ArrayList<>();
            batchesRepo.findAll().forEach(e->{
                e.getBatchCourseConnecting().forEach(f->{
                    if(f.getCourseList().getCourseName().equalsIgnoreCase(info[1]) && f.getBatches().getBatchName().equalsIgnoreCase(info[0])){
                        f.setTrainerName("");
                        f.setAssigned(false);
                        batches1.add(e);
                    }
                });
            });
            return ResponseEntity.ok(new CommonResponse(info[0]+" removed from "+assignTnr.getUserName(),""));
        }
        System.out.println(info[0]+" Batch "+info[1]);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("Can't Assign! ERROR!",""));
    }

    @CrossOrigin("*")
    @PostMapping("/trainer-schedule")
    public ResponseEntity<?> getTrainerSchedule(@RequestBody TrainerDeleteReq trainerDeleteReq){
        ArrayList<TrainerRoutinResp> arrayList = new ArrayList<>();

        batchesRepo.findAll().forEach(e->{
            e.getBatchCourseConnecting().forEach(f->{
                if(f.getTrainerName().equalsIgnoreCase(trainerDeleteReq.getUserName())){
                    TrainerRoutinResp trainerRoutinResp = new TrainerRoutinResp();
                    trainerRoutinResp.setBatchName(f.getBatches().getBatchName());
                    trainerRoutinResp.setCourseName(f.getCourseList().getCourseName());
                    trainerRoutinResp.setStartTime(f.getStartTime());
                    trainerRoutinResp.setEndTime(f.getEndTime());

                    arrayList.add(trainerRoutinResp);
                }
            });
        });

        return ResponseEntity.ok(arrayList);
    }

}
