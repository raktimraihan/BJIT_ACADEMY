package com.bjit.training.finalproject;

import com.bjit.training.finalproject.configuration.DIConfiguration;
import com.bjit.training.finalproject.model.*;
import com.bjit.training.finalproject.repo.*;
import io.jsonwebtoken.lang.Arrays;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
public class FinalProjectApplication implements CommandLineRunner {

	@Autowired
	CredentialsRepo credentialsRepo;

	@Autowired
	BatchesRepo batchesRepo;

	@Autowired
	CourseListRepo courseListRepo;

	@Autowired
	QuizzesRepo quizzesRepo;

	@Autowired
	TrainerProfileRepo trainerProfileRepo;

	@Autowired
	TraineeProfilesrepo traineeProfilesrepo;

	@Autowired
	QuizMarksConnectingRepo quizMarksConnectingRepo;

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		AnnotationConfigApplicationContext annotationConfigApplicationContext =
				new AnnotationConfigApplicationContext(DIConfiguration.class);

		trainerProfileRepo.deleteAll();
		credentialsRepo.deleteAll();
		courseListRepo.deleteAll();
		batchesRepo.deleteAll();
		quizzesRepo.deleteAll();
		traineeProfilesrepo.deleteAll();
		quizMarksConnectingRepo.deleteAll();

		credentialsRepo.save((Credentials) annotationConfigApplicationContext.getBean("A-01"));
		credentialsRepo.save((Credentials) annotationConfigApplicationContext.getBean("A-02"));

		credentialsRepo.save((Credentials) annotationConfigApplicationContext.getBean("U-01"));
		credentialsRepo.save((Credentials) annotationConfigApplicationContext.getBean("U-02"));
		credentialsRepo.save((Credentials) annotationConfigApplicationContext.getBean("U-03"));
		credentialsRepo.save((Credentials) annotationConfigApplicationContext.getBean("U-04"));
		credentialsRepo.save((Credentials) annotationConfigApplicationContext.getBean("U-05"));

		credentialsRepo.save((Credentials) annotationConfigApplicationContext.getBean("M-01"));
		credentialsRepo.save((Credentials) annotationConfigApplicationContext.getBean("M-02"));
		credentialsRepo.save((Credentials) annotationConfigApplicationContext.getBean("M-03"));
		credentialsRepo.save((Credentials) annotationConfigApplicationContext.getBean("M-04"));
		credentialsRepo.save((Credentials) annotationConfigApplicationContext.getBean("M-05"));


		batchesRepo.save((Batches) annotationConfigApplicationContext.getBean("Batch-01"));
		batchesRepo.save((Batches) annotationConfigApplicationContext.getBean("Batch-02"));
		batchesRepo.save((Batches) annotationConfigApplicationContext.getBean("Batch-03"));
		batchesRepo.save((Batches) annotationConfigApplicationContext.getBean("Batch-04"));
		batchesRepo.save((Batches) annotationConfigApplicationContext.getBean("Batch-05"));


		courseListRepo.save((CourseList) annotationConfigApplicationContext.getBean("Course-01"));
		courseListRepo.save((CourseList) annotationConfigApplicationContext.getBean("Course-02"));
		courseListRepo.save((CourseList) annotationConfigApplicationContext.getBean("Course-03"));
		courseListRepo.save((CourseList) annotationConfigApplicationContext.getBean("Course-04"));
		courseListRepo.save((CourseList) annotationConfigApplicationContext.getBean("Course-05"));
		courseListRepo.save((CourseList) annotationConfigApplicationContext.getBean("Course-06"));
		courseListRepo.save((CourseList) annotationConfigApplicationContext.getBean("Course-07"));

		quizzesRepo.save((Quizzes) annotationConfigApplicationContext.getBean("Quiz-01"));
		quizzesRepo.save((Quizzes) annotationConfigApplicationContext.getBean("Quiz-02"));
		quizzesRepo.save((Quizzes) annotationConfigApplicationContext.getBean("Quiz-03"));
		quizzesRepo.save((Quizzes) annotationConfigApplicationContext.getBean("Quiz-04"));
		quizzesRepo.save((Quizzes) annotationConfigApplicationContext.getBean("Quiz-05"));

		trainerProfileRepo.save((TrainerProfile) annotationConfigApplicationContext.getBean("Trainer-01"));
		trainerProfileRepo.save((TrainerProfile) annotationConfigApplicationContext.getBean("Trainer-02"));
		trainerProfileRepo.save((TrainerProfile) annotationConfigApplicationContext.getBean("Trainer-03"));
		trainerProfileRepo.save((TrainerProfile) annotationConfigApplicationContext.getBean("Trainer-04"));

		traineeProfilesrepo.save((TraineeProfile) annotationConfigApplicationContext.getBean("Trainee-01"));
		traineeProfilesrepo.save((TraineeProfile) annotationConfigApplicationContext.getBean("Trainee-02"));
		traineeProfilesrepo.save((TraineeProfile) annotationConfigApplicationContext.getBean("Trainee-03"));
		traineeProfilesrepo.save((TraineeProfile) annotationConfigApplicationContext.getBean("Trainee-04"));
		traineeProfilesrepo.save((TraineeProfile) annotationConfigApplicationContext.getBean("Trainee-05"));


		Optional<TraineeProfile> traineeProfile = traineeProfilesrepo.findByUserName("U-01").stream().findFirst();
		traineeProfile.get().setQuizMarksConnecting((QuizMarksConnecting) annotationConfigApplicationContext.getBean("Quiz-Mark-01"));
		traineeProfilesrepo.save(traineeProfile.get());

		Optional<TraineeProfile> traineeProfile01 = traineeProfilesrepo.findByUserName("U-02").stream().findFirst();
		traineeProfile01.get().setQuizMarksConnecting((QuizMarksConnecting) annotationConfigApplicationContext.getBean("Quiz-Mark-02"));
		traineeProfilesrepo.save(traineeProfile01.get());

		Optional<TraineeProfile> traineeProfile02 = traineeProfilesrepo.findByUserName("U-03").stream().findFirst();
		traineeProfile02.get().setQuizMarksConnecting((QuizMarksConnecting) annotationConfigApplicationContext.getBean("Quiz-Mark-03"));
		traineeProfilesrepo.save(traineeProfile02.get());

		Optional<TraineeProfile> traineeProfile03 = traineeProfilesrepo.findByUserName("U-04").stream().findFirst();
		traineeProfile03.get().setQuizMarksConnecting((QuizMarksConnecting) annotationConfigApplicationContext.getBean("Quiz-Mark-04"));
		traineeProfilesrepo.save(traineeProfile03.get());

		Optional<TraineeProfile> traineeProfile04 = traineeProfilesrepo.findByUserName("U-05").stream().findFirst();
		traineeProfile04.get().setQuizMarksConnecting((QuizMarksConnecting) annotationConfigApplicationContext.getBean("Quiz-Mark-05"));
		traineeProfilesrepo.save(traineeProfile04.get());

		Optional<Batches> batches01 = batchesRepo.findByBatchName("FB_06_Mern").stream().findFirst();
		batches01.get().setBatchCourseConnecting((BatchCourseConnecting) annotationConfigApplicationContext.getBean("Batch-Course-Connect-01"));
		batchesRepo.save(batches01.get());

		Optional<Batches> batches02 = batchesRepo.findByBatchName("FB_06_Mern").stream().findFirst();
		batches02.get().setBatchCourseConnecting((BatchCourseConnecting) annotationConfigApplicationContext.getBean("Batch-Course-Connect-02"));
		batchesRepo.save(batches02.get());

		Optional<Batches> batches03 = batchesRepo.findByBatchName("FB_06_Mern").stream().findFirst();
		batches03.get().setBatchCourseConnecting((BatchCourseConnecting) annotationConfigApplicationContext.getBean("Batch-Course-Connect-03"));
		batchesRepo.save(batches03.get());

		Optional<Batches> batches04 = batchesRepo.findByBatchName("FB_06_Java").stream().findFirst();
		batches04.get().setBatchCourseConnecting((BatchCourseConnecting) annotationConfigApplicationContext.getBean("Batch-Course-Connect-04"));
		batchesRepo.save(batches04.get());

	}
}
