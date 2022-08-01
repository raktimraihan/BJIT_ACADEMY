package com.bjit.training.finalproject.controller;

import com.bjit.training.finalproject.model.BatchCourseConnecting;
import com.bjit.training.finalproject.model.Batches;
import com.bjit.training.finalproject.model.CourseList;
import com.bjit.training.finalproject.payload.CourseListFiltered;
import com.bjit.training.finalproject.payload.CourseRegisterReq;
import com.bjit.training.finalproject.payload.TrainerDeleteReq;
import com.bjit.training.finalproject.payload.response.CommonResponse;
import com.bjit.training.finalproject.payload.response.CourseListFilteredResp;
import com.bjit.training.finalproject.repo.CourseListRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/course/*")
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    CourseListRepo courseListRepo;

    @CrossOrigin("*")
    @PostMapping("/course-register")
    public ResponseEntity<?> courseRegister(@RequestBody CourseRegisterReq courseRegisterReq){
        Optional<CourseList> courseList = courseListRepo.findByCourseName(courseRegisterReq.getCourseName());
        logger.info(courseRegisterReq.toString());

        if(!courseList.isPresent()){
            courseListRepo.save(new CourseList(courseRegisterReq.getCourseName(), courseRegisterReq.getTopic(),courseRegisterReq.getDescription()));
            return ResponseEntity.ok(new CommonResponse("Course Registered Successfully",""));
        }

        if(courseList.isPresent() || (courseList.get().getCourseName().toLowerCase().equals(courseRegisterReq.getCourseName()))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("Data Contains Already", "Can't Insert"));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("Error", "Can't Insert"));
    }

    @CrossOrigin("*")
    @PostMapping("/course-list-for-batch")
    public ResponseEntity<?> courseListForBatch(@RequestBody CourseListFiltered batchName){
        ArrayList<CourseList> courseLists = new ArrayList<>();
        ArrayList<CourseList> courseListsFinal = new ArrayList<>();
        ArrayList<String> courseNames = new ArrayList<>();

        courseListRepo.findAll().forEach(e -> {
            ArrayList<Batches> batches = new ArrayList<>();
            boolean flag = true;
            e.getBatchCourseConnecting().forEach( d -> batches.add(d.getBatches()));

            for(Batches b:batches){
                System.out.println(b.getBatchName()+" "+b.getBatchName().equalsIgnoreCase(batchName.getBatchName())+" "+batchName);
                if(b.getBatchName().equalsIgnoreCase(batchName.getBatchName())){
                    courseLists.add(e);
                }
            }
        });

        courseListRepo.findAll().forEach( e-> {
            if(!courseLists.contains(e)){
                courseNames.add(e.getCourseName());
            }
        });
        return ResponseEntity.ok(courseNames);
    }

    @CrossOrigin("*")
    @GetMapping("/course-names")
    public ResponseEntity<?> getCourseName(){
        List<String> courseLists = new ArrayList<>();
        courseListRepo.findAll().forEach(
                e -> {courseLists.add(e.getCourseName());}
        );
        return ResponseEntity.ok(courseLists);
    }

    @CrossOrigin("*")
    @PostMapping("/check-schedule")
    public ResponseEntity<?> getCheckSchedule(@RequestBody TrainerDeleteReq trainerDeleteReq){
        ArrayList<BatchCourseConnecting> batchCourseConnectings = new ArrayList<>();
        ArrayList<BatchCourseConnecting> batchCourseConnectingsTrainer = new ArrayList<>();
        ArrayList<CourseListFilteredResp> batchCourseConnectingsResp = new ArrayList<>();
        ArrayList<BatchCourseConnecting> batchCourseConnectings1 = new ArrayList<>();

        courseListRepo.findAll().forEach(e ->{
            e.getBatchCourseConnecting().forEach(f-> batchCourseConnectings.add(f));
        });

        batchCourseConnectings.forEach(e ->{
            if(e.getTrainerName().equalsIgnoreCase(trainerDeleteReq.getUserName())){
                batchCourseConnectingsTrainer.add(e);
            }
        });

        Collections.sort(batchCourseConnectings,(o1, o2) -> {
            Double startTime = Double.parseDouble(o1.getStartTime().toString());
            Double startTime01 = Double.parseDouble(o2.getStartTime().toString());
            if (startTime > startTime01) {
                return 1;
            }
            else return -1;
        });

        Collections.sort(batchCourseConnectingsTrainer,(o1, o2) -> {
            Double startTime = Double.parseDouble(o1.getStartTime().toString());
            Double startTime01 = Double.parseDouble(o2.getStartTime().toString());
            if (startTime > startTime01) {
                return 1;
            }
            else return -1;
        });

        for(BatchCourseConnecting bat: batchCourseConnectings){
            System.out.println(bat.getTrainerName()+" "+bat.getCourseList().getCourseName().toString()+
                    " "+bat.getStartTime()+" "+bat.getEndTime()+" "+bat.getBatches().getBatchName().toString());
            batchCourseConnectings1.add(bat);
        }
        System.out.println("Trainer Data--------");

        if(batchCourseConnectingsTrainer.isEmpty()){
            for(BatchCourseConnecting bat: batchCourseConnectings){
                batchCourseConnectingsResp.add(new CourseListFilteredResp(bat.getBatches().getBatchName(),
                        bat.getStartTime(),
                        bat.getEndTime(),
                        bat.getCourseList().getCourseName()));
            }
            return ResponseEntity.ok(batchCourseConnectingsResp);
        }

        for(BatchCourseConnecting bat: batchCourseConnectingsTrainer){
            System.out.println(bat.getTrainerName()+" "+bat.getCourseList().getCourseName().toString()+
                    " "+bat.getStartTime()+" "+bat.getEndTime()+" "+bat.getBatches().getBatchName().toString());
        }

        System.out.println("Conflicting Data--------");
        for(int i=0; i<batchCourseConnectings.size(); i++){
            for(int j=0; j<batchCourseConnectingsTrainer.size(); j++){

                Double endTimeParent = Double.parseDouble(batchCourseConnectings.get(i).getEndTime().toString());
                Double endTimeChild = Double.parseDouble(batchCourseConnectingsTrainer.get(j).getEndTime().toString());
                Double startTimeChild = Double.parseDouble(batchCourseConnectingsTrainer.get(j).getStartTime().toString());

                if(((endTimeParent >= startTimeChild && endTimeParent <= endTimeChild) ||
                        (endTimeParent >= startTimeChild && endTimeParent > endTimeChild))){

                    System.out.println(batchCourseConnectings.get(i).getBatches().getBatchName()+
                            " "+batchCourseConnectings.get(i).getStartTime()+
                            " "+batchCourseConnectings.get(i).getEndTime()+
                            " "+batchCourseConnectings.get(i).getCourseList().getCourseName()+
                            " "+batchCourseConnectings.get(i).getTrainerName());

                    batchCourseConnectings1.remove(batchCourseConnectings.get(i));
                }
            }
        }

        for(BatchCourseConnecting bat: batchCourseConnectings1){
            batchCourseConnectingsResp.add(new CourseListFilteredResp(bat.getBatches().getBatchName(),
                            bat.getStartTime(),
                            bat.getEndTime(),
                            bat.getCourseList().getCourseName()));
        }

        return ResponseEntity.ok(batchCourseConnectingsResp);
    }

    @CrossOrigin("*")
    @PostMapping("/check-schedule-matching")
    public ResponseEntity<?> getCheckScheduleMatching(@RequestBody TrainerDeleteReq trainerDeleteReq){
        ArrayList<BatchCourseConnecting> batchCourseConnectings = new ArrayList<>();
        ArrayList<BatchCourseConnecting> batchCourseConnectingsTrainer = new ArrayList<>();
        ArrayList<CourseListFilteredResp> batchCourseConnectingsResp = new ArrayList<>();
        ArrayList<BatchCourseConnecting> batchCourseConnectings1 = new ArrayList<>();

        courseListRepo.findAll().forEach(e ->{
            e.getBatchCourseConnecting().forEach(f-> batchCourseConnectings.add(f));
        });

        batchCourseConnectings.forEach(e ->{
            if(e.getTrainerName().equalsIgnoreCase(trainerDeleteReq.getUserName())){
                batchCourseConnectingsTrainer.add(e);
            }
        });

        Collections.sort(batchCourseConnectings,(o1, o2) -> {
            Double startTime = Double.parseDouble(o1.getStartTime().toString());
            Double startTime01 = Double.parseDouble(o2.getStartTime().toString());
            if (startTime > startTime01) {
                return 1;
            }
            else return -1;
        });

        Collections.sort(batchCourseConnectingsTrainer,(o1, o2) -> {
            Double startTime = Double.parseDouble(o1.getStartTime().toString());
            Double startTime01 = Double.parseDouble(o2.getStartTime().toString());
            if (startTime > startTime01) {
                return 1;
            }
            else return -1;
        });

        for(BatchCourseConnecting bat: batchCourseConnectings){
            System.out.println(bat.getTrainerName()+" "+bat.getCourseList().getCourseName().toString()+
                    " "+bat.getStartTime()+" "+bat.getEndTime()+" "+bat.getBatches().getBatchName().toString());
            batchCourseConnectings1.add(bat);
        }

        System.out.println("Trainer Data--------");
        for(BatchCourseConnecting bat: batchCourseConnectingsTrainer){
            System.out.println(bat.getTrainerName()+" "+bat.getCourseList().getCourseName().toString()+
                    " "+bat.getStartTime()+" "+bat.getEndTime()+" "+bat.getBatches().getBatchName().toString());
            batchCourseConnectingsResp.add(new CourseListFilteredResp(bat.getBatches().getBatchName(),
                    bat.getStartTime(),
                    bat.getEndTime(),
                    bat.getCourseList().getCourseName()));

        }
        System.out.println("Conflicting Data--------");

        return ResponseEntity.ok(batchCourseConnectingsResp);
    }


}


