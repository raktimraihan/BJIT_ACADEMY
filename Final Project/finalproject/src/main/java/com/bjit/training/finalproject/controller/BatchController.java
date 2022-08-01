package com.bjit.training.finalproject.controller;

import com.bjit.training.finalproject.model.BatchCourseConnecting;
import com.bjit.training.finalproject.model.Batches;
import com.bjit.training.finalproject.model.CourseList;
import com.bjit.training.finalproject.payload.BatchConnectingReq;
import com.bjit.training.finalproject.payload.BatchCourseDeleteReq;
import com.bjit.training.finalproject.payload.BatchDetailsResp;
import com.bjit.training.finalproject.payload.response.BatchRegisterRequest;
import com.bjit.training.finalproject.payload.response.CommonResponse;
import com.bjit.training.finalproject.repo.BatchesRepo;
import com.bjit.training.finalproject.repo.CourseListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.DecimalFormat;
import java.util.*;

@RestController()
@RequestMapping("/batch/*")
public class BatchController {

    @Autowired
    BatchesRepo batchesRepo;

    @Autowired
    CourseListRepo courseListRepo;

    @PostMapping("/batch-register")
    @CrossOrigin("*")
    public ResponseEntity<?> batchRegister(@RequestBody BatchRegisterRequest batchRegisterRequest){
        Optional<Batches> batches = batchesRepo.findByBatchName(batchRegisterRequest.getBatchName()).stream().findFirst();

        if(batchRegisterRequest.getBatchName().length() < 2){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("Field can't be empty", "Can't Insert"));
        }

        if(!batches.isPresent()){
            batchesRepo.save(new Batches(batchRegisterRequest.getBatchName(), batchRegisterRequest.isActive()));
            return ResponseEntity.ok(new CommonResponse("Data Inserted Successfully",""));
        }

        if(batches.isPresent() || (batches.get().getBatchName().toLowerCase(Locale.ROOT).equals(batchRegisterRequest.getBatchName()))){
            String message = "Batch Name exists. Try Different Name";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("Data Contains", "Can't Insert"));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("Internal ERROR!", "Can't Insert"));
    }

    @CrossOrigin("*")
    @GetMapping("/batch-names")
    public ResponseEntity<?> getbatchName(){
        List<String> batches = new ArrayList<>();
        batchesRepo.findAll().forEach(e->{
                if(e.getActive()){
                    batches.add(e.getBatchName());
                }
        });

        return ResponseEntity.ok(batches);
    }

    @CrossOrigin("*")
    @PostMapping("/batch-connecting-insertion")
    public ResponseEntity<?> batchCourseConnectingInsertion(@RequestBody BatchConnectingReq batchReq){

        if(Double.parseDouble(batchReq.getStartHour()) >= Double.parseDouble(batchReq.getEndHour())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("End Time Can't be Lower", "Can't Insert"));
        }

        Optional<CourseList> courseList = courseListRepo.findByCourseName(batchReq.getCourseName()).stream().findFirst();
        Optional<Batches> batches = batchesRepo.findByBatchName(batchReq.getBatchName()).stream().findFirst();
        System.out.println(batchReq.toString());
        Double startHourDouble = Double.parseDouble(batchReq.getStartHour());
        Double endHourDouble = Double.parseDouble(batchReq.getEndHour());
        DecimalFormat df = new DecimalFormat("##.##");

        ArrayList<BatchCourseConnecting> batchCourseConnectings = new ArrayList<>();
        courseListRepo.findAll().forEach(e ->{
            e.getBatchCourseConnecting().forEach(f-> {
                if(f.getBatches().getBatchName().equalsIgnoreCase(batchReq.getBatchName())){
                    batchCourseConnectings.add(f);
                }
            });
        });

        Collections.sort(batchCourseConnectings,(o1, o2) -> {
            Double startTime = Double.parseDouble(o1.getStartTime().toString());
            Double startTime01 = Double.parseDouble(o2.getStartTime().toString());
            if (startTime > startTime01) {
                return 1;
            }
            else return -1;
        });

        System.out.println("Batch Data---------------");
        System.out.println(startHourDouble+" Hour "+endHourDouble);
        System.out.println("Batch Data---------------");
        for(BatchCourseConnecting bat: batchCourseConnectings){
            System.out.println(bat.getTrainerName()+" "+bat.getCourseList().getCourseName().toString()+
                    " "+bat.getStartTime()+" "+bat.getEndTime()+" "+bat.getBatches().getBatchName().toString());
        }

        for(BatchCourseConnecting bat: batchCourseConnectings){
            Double startHourParent = bat.getStartTime();
            Double endHourParent = bat.getEndTime();
            System.out.println("Batch Data---------------");
            System.out.println(startHourParent+" Hour "+endHourParent);
            if(((endHourDouble >= startHourParent && endHourDouble <= endHourParent))){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("Courses Already Assigned in this Hour", "Can't Insert"));
            }

        }

        BatchCourseConnecting batchCourseConnecting = new BatchCourseConnecting(batches.get(),courseList.get(),
                Double.valueOf(df.format(startHourDouble)),  Double.valueOf(df.format(endHourDouble)));
        if(batches.get().getBatchCourseConnecting().contains(batchCourseConnecting)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("Data Contains", "Can't Insert"));
        }

        batches.get().setBatchCourseConnecting(batchCourseConnecting);
        batchesRepo.save(batches.get());
        return ResponseEntity.ok(new CommonResponse(batchReq.getCourseName()+" Course Assigned to "+batchReq.getBatchName(),""));
    }

    @CrossOrigin("*")
    @GetMapping("/batches-schedule")
    public ResponseEntity<?> getBatchesSchedule(){
        ArrayList<String> response = new ArrayList<>();
        batchesRepo.findAll().forEach(e->{
            ArrayList<BatchCourseConnecting> batchCourseConnectings = new ArrayList<>();
            e.getBatchCourseConnecting().forEach(d -> batchCourseConnectings.add(d));

            for(BatchCourseConnecting b: batchCourseConnectings){
                System.out.println(b.getCourseList());
                if(!b.getAssigned()){
                    response.add(b.getCourseList().getCourseName()+"-->"+b.getBatches().getBatchName()+"-->"+b.getStartTime()+"-->"+b.getEndTime());
                }
            }
        });
        return ResponseEntity.ok(response);
    }

    @CrossOrigin("*")
    @PostMapping("/get-batch-details")
    public ResponseEntity<?> getBatchDetails(){
        ArrayList<BatchDetailsResp> batchDetailsResps = new ArrayList<>();

        batchesRepo.findAll().forEach(e->{
            e.getBatchCourseConnecting().forEach(f->{
                BatchDetailsResp batchDetailsResp = new BatchDetailsResp();
                batchDetailsResp.setBatchName(e.getBatchName());
                batchDetailsResp.setCourseName(f.getCourseList().getCourseName());
                batchDetailsResp.setStartTime(f.getStartTime());
                batchDetailsResp.setEndTime(f.getEndTime());
                batchDetailsResp.setActiveStatus(e.getActive());
                batchDetailsResp.setTrainerName(f.getTrainerName());

                batchDetailsResps.add(batchDetailsResp);
            });
        });

        return ResponseEntity.ok(batchDetailsResps);
    }

    @CrossOrigin
    @PostMapping("/delete-batch-course")
    public  ResponseEntity<?> deleteBatchDetails(@RequestBody BatchCourseDeleteReq batchCourseDeleteReq){
        System.out.println(batchCourseDeleteReq.toString());
        Optional<Batches> batches = batchesRepo.findByBatchName(batchCourseDeleteReq.getBatchName()).stream().findFirst();
        if(!batches.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("Not Found! Can't Process", "Can't Insert"));
        }

        if(batches.isPresent()){

            batchesRepo.findAll().forEach(e->{
                if(e.getBatchName().equalsIgnoreCase(batchCourseDeleteReq.getBatchName())){
                    e.getBatchCourseConnecting().forEach(f->{
                        if(f.getCourseList().getCourseName().equalsIgnoreCase(batchCourseDeleteReq.getCourseName())){
                            e.setBatchCourseConnecting(new HashSet<>());
                        }
                    });
                }
            });

            return ResponseEntity.ok("Deleted!");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse("ERROR!", "Can't Insert"));

    }
}
