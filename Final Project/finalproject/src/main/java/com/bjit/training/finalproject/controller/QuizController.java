package com.bjit.training.finalproject.controller;

import com.bjit.training.finalproject.model.*;
import com.bjit.training.finalproject.payload.CourseListFiltered;
import com.bjit.training.finalproject.payload.QuizBatchConnecting;
import com.bjit.training.finalproject.payload.QuizRegReq;
import com.bjit.training.finalproject.payload.TrainerDeleteReq;
import com.bjit.training.finalproject.payload.response.CommonResponse;
import com.bjit.training.finalproject.payload.response.QuizAllDataResp;
import com.bjit.training.finalproject.payload.response.QuizMinimaldataResp;
import com.bjit.training.finalproject.repo.BatchesRepo;
import com.bjit.training.finalproject.repo.QuizzesRepo;
import com.bjit.training.finalproject.repo.TraineeProfilesrepo;
import com.bjit.training.finalproject.repo.TrainerProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/quiz/*")
public class QuizController {

    @Autowired
    QuizzesRepo quizzesRepo;

    @Autowired
    BatchesRepo batchesRepo;

    @Autowired
    TraineeProfilesrepo traineeProfilesrepo;

    @Autowired
    TrainerProfileRepo trainerProfileRepo;

    @Autowired
    EntityManager entityManager;

    @PostMapping("/quiz-register")
    @CrossOrigin("*")
    public ResponseEntity<?> quizRgistration(@RequestBody QuizRegReq qzReq){

        System.out.println(qzReq.toString());
        Optional<Quizzes> quizzes = quizzesRepo.findByTitle(qzReq.getTitle()).stream().findFirst();
        if(quizzes.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new CommonResponse("Quiz has been created previously.",""));
        }
        else{
            Double marks = Double.parseDouble(qzReq.getTotalMarks());
            try{
                long duration = Integer.parseInt(qzReq.getDuration())*1000;
                if(duration > 5000){
                    quizzesRepo.save(new Quizzes(marks, qzReq.getTitle(), duration, qzReq.isActive()));
                    return ResponseEntity.ok(new CommonResponse(qzReq.getTitle()+" Created.",""));
                }
                else{
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new CommonResponse("Quiz Can't be less than 5 minutes",""));
                }
            }
            catch(Exception ex){
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new CommonResponse("Check Data Again.",""));
            }
        }
    }

    @PostMapping("/quiz-list-per-batch")
    @CrossOrigin("*")
    public ResponseEntity<?> getQuizListPerBatch(@RequestBody CourseListFiltered batchName){
        ArrayList<Quizzes> quizzesArrayList = new ArrayList<>();
        ArrayList<String> quizNames = new ArrayList<>();

        quizzesRepo.findAll().forEach(e -> {
            ArrayList<Batches> batches = new ArrayList<>();
            e.getBatchesSet().forEach(d -> batches.add(d));

            for(Batches batch: batches){
                if(batch.getBatchName().equalsIgnoreCase(batchName.getBatchName())){
                    quizzesArrayList.add(e);
                }
            }
        });

        quizzesRepo.findAll().forEach(e -> {
            if(!quizzesArrayList.contains(e)){
                quizNames.add(e.getTitle());
            }
        });
        return ResponseEntity.ok(quizNames);
    }

    @CrossOrigin("*")
    @PostMapping("/quiz-batch-register")
    public ResponseEntity<?> insertQuizBatchConnecting(@RequestBody QuizBatchConnecting qzCntBatch){
        System.out.println(qzCntBatch.toString());
        Optional<Batches> batches = batchesRepo.findByBatchName(qzCntBatch.getBatchName()).stream().findFirst();
        Optional<Quizzes> quizzes = quizzesRepo.findByTitle(qzCntBatch.getQuizName()).stream().findFirst();
        ArrayList<String> responseList = new ArrayList<>();

        if((batches.isPresent()&&quizzes.isPresent()) && quizzes.get().getBatchesSet().contains(batches.get())){
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new CommonResponse("Quiz is Already Assigned",""));
        }

        if(batches.isPresent() && quizzes.isPresent()){
            quizzes.get().setBatchesSet(batches.get());
            quizzesRepo.save(quizzes.get());
            ArrayList<TraineeProfile> traineeProfiles = new ArrayList<>();

            traineeProfilesrepo.findAll().forEach(e->{
                if(e.getBatches().getBatchName().equalsIgnoreCase(qzCntBatch.getBatchName())){
                    traineeProfiles.add(e);
                }
            });

            for(TraineeProfile t: traineeProfiles){
                QuizMarksConnecting quizMarksConnecting = new QuizMarksConnecting(t, quizzes.get());
                quizzes.get().setQuizMarksConnecting(quizMarksConnecting);
                responseList.add("Student "+t.getUserName()+" having Name: "+t.getName()+" from Batch: "+t.getBatches().getBatchName()+" is assigned to Quiz: "+quizzes.get().getTitle());
                quizzesRepo.save(quizzes.get());
            }
            responseList.add(qzCntBatch.getQuizName()+" assigned to "+qzCntBatch.getBatchName());
            return ResponseEntity.ok(new CommonResponse(responseList.toString(),""));
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new CommonResponse("Error in Inserting",""));
    }

    @PostMapping("/quiz-list-per-batch-no-filter")
    @CrossOrigin("*")
    public ResponseEntity<?> getQuizListPerBatchNoFilter(@RequestBody CourseListFiltered batchName){
        ArrayList<Quizzes> quizzesArrayList = new ArrayList<>();
        ArrayList<String> quizNames = new ArrayList<>();

        quizzesRepo.findAll().forEach(e -> {
            ArrayList<Batches> batches = new ArrayList<>();
            e.getBatchesSet().forEach(d -> batches.add(d));

            for(Batches batch: batches){
                if(batch.getBatchName().equalsIgnoreCase(batchName.getBatchName())){
                    quizzesArrayList.add(e);
                }
            }
        });

        quizzesRepo.findAll().forEach(e -> {
            if(quizzesArrayList.contains(e)){
                quizNames.add(e.getTitle());
            }
        });
        return ResponseEntity.ok(quizNames);
    }

    @GetMapping("/get-all-quiz-reviewed")
    @CrossOrigin("*")
    public ResponseEntity<?> getAllQuizData(){
        ArrayList<QuizAllDataResp> quizAllDataResps = new ArrayList<>();

        quizzesRepo.findAll().forEach(e -> {
            e.getQuizMarksConnecting().forEach(f->{
                if(f.getReviewed()){
                    QuizAllDataResp quizAllDataResp = new QuizAllDataResp();
                    quizAllDataResp.setTitle(f.getQuizzes().getTitle());
                    quizAllDataResp.setBatchName(f.getTraineeProfile().getBatches().getBatchName());
                    quizAllDataResp.setTraineeName(f.getTraineeProfile().getName());
                    quizAllDataResp.setTraineeUserName(f.getTraineeProfile().getUserName());
                    quizAllDataResp.setTotalMarks(f.getQuizzes().getTotalMarks());
                    quizAllDataResp.setAttainedMarks(f.getAttainedMarks());
                    quizAllDataResp.setReviewerUserName(f.getReviewer());
                    String name="";
                    try{
                        Optional<TrainerProfile> trainerProfile = trainerProfileRepo.findByUserName(f.getReviewer()).stream().findFirst();
                        name = trainerProfile.get().getName();
                    }catch(Exception ex){

                    }finally {

                    }
                    quizAllDataResp.setReviewerName(name);
                    quizAllDataResps.add(quizAllDataResp);
                }
            });
        });

        return ResponseEntity.ok(quizAllDataResps);
    }

    @GetMapping("/get-all-quiz-not-reviewed")
    @CrossOrigin("*")
    public ResponseEntity<?> getAllQuizDataNonReviewed(){
        ArrayList<QuizAllDataResp> quizAllDataResps = new ArrayList<>();
        ArrayList<QuizBatchConnecting> arrayList = new ArrayList<>();

        quizzesRepo.findAll().forEach(e -> {
            e.getQuizMarksConnecting().forEach(f->{
                if(!f.getReviewed()){
                    QuizAllDataResp quizAllDataResp = new QuizAllDataResp();
                    quizAllDataResp.setTitle(f.getQuizzes().getTitle());
                    quizAllDataResp.setBatchName(f.getTraineeProfile().getBatches().getBatchName());
                    quizAllDataResp.setTraineeName(f.getTraineeProfile().getName());
                    quizAllDataResp.setTraineeUserName(f.getTraineeProfile().getUserName());
                    quizAllDataResp.setTotalMarks(f.getQuizzes().getTotalMarks());
                    quizAllDataResp.setAttainedMarks(f.getAttainedMarks());
                    quizAllDataResp.setReviewerName(f.getReviewer());

                    try{
                        Optional<TrainerProfile> trainerProfile = trainerProfileRepo.findByUserName(f.getReviewer()).stream().findFirst();
                        quizAllDataResp.setReviewerName(trainerProfile.get().getName());
                    }catch(Exception ex){

                    }finally {
                        quizAllDataResp.setReviewerName("");
                    }
                    quizAllDataResps.add(quizAllDataResp);
                }
            });
        });

        return ResponseEntity.ok(quizAllDataResps);
    }

    @PostMapping("/assign-quiz-grading")
    @CrossOrigin("*")
    @Transactional
    public ResponseEntity<?> gradeTheAssignment(@RequestBody QuizAllDataResp quizAllDataResp){
        Optional<TraineeProfile> traineeProfile = traineeProfilesrepo.findByUserName(quizAllDataResp.getTraineeUserName()).stream().findFirst();
        Optional<Quizzes> quizzes = quizzesRepo.findByTitle(quizAllDataResp.getTitle()).stream().findFirst();

        if(traineeProfile.isPresent() && quizzes.isPresent()){
            QuizMarksConnecting quizMarksConnecting = new QuizMarksConnecting(traineeProfile.get(),quizzes.get(), quizAllDataResp.getAttainedMarks());
            quizMarksConnecting.setReviewed(true);
            quizMarksConnecting.setSubmitted(true);
            quizMarksConnecting.setReviewer(quizAllDataResp.getReviewerUserName());

            traineeProfilesrepo.findAll().forEach(e->{
                if(e.getUserName().equalsIgnoreCase(quizAllDataResp.getTraineeUserName())){
                    e.getQuizMarksConnecting().forEach(f->{
                        if(f.getTraineeProfile().getUserName().equalsIgnoreCase(quizAllDataResp.getTraineeUserName())
                        && f.getQuizzes().getTitle().equalsIgnoreCase(quizAllDataResp.getTitle())){
                            f.setReviewer(quizAllDataResp.getReviewerUserName());
                            f.setReviewed(true);
                            f.setAttainedMarks(quizAllDataResp.getAttainedMarks());
                            f.setSubmitted(true);
                            return;
                        }
                    });
                }
            });

            return ResponseEntity.ok(new CommonResponse(quizAllDataResp.getTraineeUserName()+ " Graded!",""));
        }
        return ResponseEntity.badRequest().body(new CommonResponse("Error!",""));
    }

    @PostMapping("/get-user-per-submission")
    @CrossOrigin("*")
    public ResponseEntity<?> getAllQuizDataPerUser(@RequestBody TrainerDeleteReq trainerDeleteReq){
        ArrayList<QuizAllDataResp> quizAllDataResps = new ArrayList<>();

        quizzesRepo.findAll().forEach(e -> {
            e.getQuizMarksConnecting().forEach(f->{
                if(!f.getReviewed() && !f.getSubmitted() && f.getTraineeProfile().getUserName().equalsIgnoreCase(trainerDeleteReq.getUserName())){
                    QuizAllDataResp quizAllDataResp = new QuizAllDataResp();
                    quizAllDataResp.setTitle(f.getQuizzes().getTitle());
                    quizAllDataResp.setBatchName(f.getTraineeProfile().getBatches().getBatchName());
                    quizAllDataResp.setTraineeName(f.getTraineeProfile().getName());
                    quizAllDataResp.setTraineeUserName(f.getTraineeProfile().getUserName());
                    quizAllDataResp.setTotalMarks(f.getQuizzes().getTotalMarks());
                    quizAllDataResp.setAttainedMarks(f.getAttainedMarks());
                    quizAllDataResp.setReviewerUserName(f.getReviewer());
                    String name="";
                    try{
                        Optional<TrainerProfile> trainerProfile = trainerProfileRepo.findByUserName(f.getReviewer()).stream().findFirst();
                        name = trainerProfile.get().getName();
                    }catch(Exception ex){

                    }finally {

                    }
                    quizAllDataResp.setReviewerName(name);
                    quizAllDataResps.add(quizAllDataResp);
                }
            });
        });

        return ResponseEntity.ok(quizAllDataResps);
    }

    @PostMapping("/quiz-assign-submission")
    @CrossOrigin("*")
    @Transactional
    public ResponseEntity<?> submitTheAssignment(@RequestBody QuizMinimaldataResp quizAllDataResp){
        Optional<TraineeProfile> traineeProfile = traineeProfilesrepo.findByUserName(quizAllDataResp.getTraineeUserName()).stream().findFirst();
        Optional<Quizzes> quizzes = quizzesRepo.findByTitle(quizAllDataResp.getTitle()).stream().findFirst();

        if(traineeProfile.isPresent() && quizzes.isPresent()){
            traineeProfilesrepo.findAll().forEach(e->{
                if(e.getUserName().equalsIgnoreCase(quizAllDataResp.getTraineeUserName())){
                    e.getQuizMarksConnecting().forEach(f->{
                        if(f.getTraineeProfile().getUserName().equalsIgnoreCase(quizAllDataResp.getTraineeUserName())
                                && f.getQuizzes().getTitle().equalsIgnoreCase(quizAllDataResp.getTitle())){
                            f.setSubmitted(true);
                            return;
                        }
                    });
                }
            });
            return ResponseEntity.ok(new CommonResponse(quizAllDataResp.getTraineeUserName()+ " submitted!",""));
        }
        return ResponseEntity.badRequest().body(new CommonResponse("Error!",""));
    }

    @PostMapping("/get-result-user")
    @CrossOrigin("*")
    public ResponseEntity<?> getResultUser(@RequestBody TrainerDeleteReq trainerDeleteReq){
        ArrayList<QuizAllDataResp> quizAllDataResps = new ArrayList<>();

        quizzesRepo.findAll().forEach(e -> {
            e.getQuizMarksConnecting().forEach(f->{
                if(f.getReviewed() && f.getSubmitted() && f.getTraineeProfile().getUserName().equalsIgnoreCase(trainerDeleteReq.getUserName())){
                    QuizAllDataResp quizAllDataResp = new QuizAllDataResp();
                    quizAllDataResp.setTitle(f.getQuizzes().getTitle());
                    quizAllDataResp.setBatchName(f.getTraineeProfile().getBatches().getBatchName());
                    quizAllDataResp.setTraineeName(f.getTraineeProfile().getName());
                    quizAllDataResp.setTraineeUserName(f.getTraineeProfile().getUserName());
                    quizAllDataResp.setTotalMarks(f.getQuizzes().getTotalMarks());
                    quizAllDataResp.setAttainedMarks(f.getAttainedMarks());
                    quizAllDataResp.setReviewerUserName(f.getReviewer());
                    String name="";
                    try{
                        Optional<TrainerProfile> trainerProfile = trainerProfileRepo.findByUserName(f.getReviewer()).stream().findFirst();
                        name = trainerProfile.get().getName();
                    }catch(Exception ex){

                    }finally {

                    }
                    quizAllDataResp.setReviewerName(name);
                    quizAllDataResps.add(quizAllDataResp);
                }
            });
        });
        return ResponseEntity.ok(quizAllDataResps);
    }

    @GetMapping("/get-all-quiz-submitted")
    @CrossOrigin("*")
    public ResponseEntity<?> getAllQuizDataSubmitted(){
        ArrayList<QuizAllDataResp> quizAllDataResps = new ArrayList<>();

        quizzesRepo.findAll().forEach(e -> {
            e.getQuizMarksConnecting().forEach(f->{
                if(!f.getReviewed() && f.getSubmitted()){
                    QuizAllDataResp quizAllDataResp = new QuizAllDataResp();
                    quizAllDataResp.setTitle(f.getQuizzes().getTitle());
                    quizAllDataResp.setBatchName(f.getTraineeProfile().getBatches().getBatchName());
                    quizAllDataResp.setTraineeName(f.getTraineeProfile().getName());
                    quizAllDataResp.setTraineeUserName(f.getTraineeProfile().getUserName());
                    quizAllDataResp.setTotalMarks(f.getQuizzes().getTotalMarks());
                    quizAllDataResp.setAttainedMarks(f.getAttainedMarks());
                    quizAllDataResp.setReviewerUserName(f.getReviewer());
                    String name="";
                    try{
                        Optional<TrainerProfile> trainerProfile = trainerProfileRepo.findByUserName(f.getReviewer()).stream().findFirst();
                        name = trainerProfile.get().getName();
                    }catch(Exception ex){

                    }finally {

                    }
                    quizAllDataResp.setReviewerName(name);
                    quizAllDataResps.add(quizAllDataResp);
                }
            });
        });

        return ResponseEntity.ok(quizAllDataResps);
    }

}
