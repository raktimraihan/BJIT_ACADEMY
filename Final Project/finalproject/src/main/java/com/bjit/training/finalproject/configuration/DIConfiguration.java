package com.bjit.training.finalproject.configuration;

import com.bjit.training.finalproject.model.*;
import com.bjit.training.finalproject.security.WebSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;

@Configuration
public class DIConfiguration {

    PasswordEncoder passwordEncoder = new WebSecurityConfig().getPasswordEncoder();

    //Users
    @Bean("A-01")
    public Credentials adminRoleTest() {
        Credentials credentials = new Credentials("A-01", passwordEncoder.encode("1234"), "ROLE_ADMIN");
        return credentials;
    }

    @Bean("A-02")
    public Credentials adminRoleTest01() {
        Credentials credentials = new Credentials("A-02", passwordEncoder.encode("1234"), "ROLE_ADMIN");
        return credentials;
    }

    @Bean("U-01")
    public Credentials userRoleTest() {
        Credentials credentials = new Credentials("U-01", passwordEncoder.encode("1234"), "ROLE_USER");
        return credentials;
    }

    @Bean("U-02")
    public Credentials userRoleTest01() {
        Credentials credentials = new Credentials("U-02", passwordEncoder.encode("1234"), "ROLE_USER");
        return credentials;
    }

    @Bean("U-03")
    public Credentials userRoleTest03() {
        Credentials credentials = new Credentials("U-03", passwordEncoder.encode("1234"), "ROLE_USER");
        return credentials;
    }

    @Bean("U-04")
    public Credentials userRoleTest04() {
        Credentials credentials = new Credentials("U-04", passwordEncoder.encode("1234"), "ROLE_USER");
        return credentials;
    }

    @Bean("U-05")
    public Credentials userRoleTest05() {
        Credentials credentials = new Credentials("U-05", passwordEncoder.encode("1234"), "ROLE_USER");
        return credentials;
    }

    @Bean("M-01")
    public Credentials moderatorCredential01() {
        Credentials credentials = new Credentials("M-01", passwordEncoder.encode("1234"), "ROLE_MODERATOR");
        return credentials;
    }

    @Bean("M-02")
    public Credentials moderatorCredential02() {
        Credentials credentials = new Credentials("M-02", passwordEncoder.encode("1234"), "ROLE_MODERATOR");
        return credentials;
    }

    @Bean("M-03")
    public Credentials moderatorCredential03() {
        Credentials credentials = new Credentials("M-03", passwordEncoder.encode("1234"), "ROLE_MODERATOR");
        return credentials;
    }

    @Bean("M-04")
    public Credentials moderatorCredential04() {
        Credentials credentials = new Credentials("M-04", passwordEncoder.encode("1234"), "ROLE_TRAINER");
        return credentials;
    }

    @Bean("M-05")
    public Credentials moderatorCredential05() {
        Credentials credentials = new Credentials("M-05", passwordEncoder.encode("1234"), "ROLE_TRAINER");
        return credentials;
    }


    //Batches
    @Bean("Batch-01")
    public Batches batchCreation01() {
        Batches batches = new Batches("FB_06_Mern", true);
        return batches;
    }

    @Bean("Batch-02")
    public Batches batchCreation02() {
        Batches batches = new Batches("FB_06_Java", true);
        return batches;
    }

    @Bean("Batch-03")
    public Batches batchCreation03() {
        Batches batches = new Batches("FB_06_Mobile", true);
        return batches;
    }

    @Bean("Batch-04")
    public Batches batchCreation04() {
        Batches batches = new Batches("FB_05_Mern", true);
        return batches;
    }

    @Bean("Batch-05")
    public Batches batchCreation05() {
        Batches batches = new Batches("FB_07_Java", true);
        return batches;
    }

    //Courses
    @Bean("Course-01")
    public CourseList courseCreation01() {
        CourseList courseList = new CourseList("Japanese Culture and Language (I)", "Culture and Language", "Japan is a strand of islands, or archipelago, that lies to the east of the coast of China. " + "120 miles of water separate Japan from the Asian mainland. Archeologists think people have inhabited " + "the islands for about 30,000 years. During that extensive period of time, this country has alternated " + "between sharing trade and ideas with the outside world and withdrawing into itself. As a result, " + "Japanese culture is a fusion of trends from around the globe, each marked with a uniquely Japanese stamp.");
        return courseList;
    }

    @Bean("Course-02")
    public CourseList courseCreation02() {
        CourseList courseList = new CourseList("Bengali Culture and Language (I)", "Culture and Language", "Bangali Culture is perceived as enlightenment and excellence of taste in fine arts, " + "humanities and knowledge, but in broader terms, it is an integrated pattern of human knowledge, " + "behaviour, beliefs, customs, morals, conventions, shared attitudes, social organisation and achievements. " + "Anthropologically, culture is a complex idea. Since all cultures are unique in origin, development, " + "value system and organisation, one particular culture differs from another both in outline, expression and " + "essence. Bangali culture is the outcome of the accumulation and synthesis of many different ethnic and " + "religious groups and subgroups and varied classes interacting and influencing each other for centuries. The coexistence and continuation of disparate elements of culture for centuries have created a unique Bangaliness, " + "which can be identified as Bangali culture, and defined as the culture of \"Bengal\" and of the Bengali" + " speaking people.");
        return courseList;
    }

    @Bean("Course-03")
    public CourseList courseCreation03() {
        CourseList courseList = new CourseList("Algorithm I", "Analytics",
        "Algorithms course gives you an introduction to algorithms, including sorting and search algorithms, graph algorithms, and geometric algorithms. Other courses include algorithms related to specific disciplines including things like C Programming, data structures, graph theory, and quantum computers.");
        return courseList;
    }

    @Bean("Course-04")
    public CourseList courseCreation04() {
        CourseList courseList = new CourseList("JavaScript Advanced", "Programming",
                "Main course contains 2 parts which cover JavaScript as a programming language and working with a browser. There are also additional series of thematic articles.");
        return courseList;
    }

    @Bean("Course-05")
    public CourseList courseCreation05() {
        CourseList courseList = new CourseList("JavaEE Advanced", "Programming",
                "Main course contains 2 parts which cover JavaEE as a programming language and working with a Server. There are also additional series of thematic articles.");
        return courseList;
    }

    @Bean("Course-06")
    public CourseList courseCreation06() {
        CourseList courseList = new CourseList("Software Testing", "Programming",
                "Main course contains 2 parts which cover Software Testing and working with a Automated Testing. There are also additional series of thematic articles.");
        return courseList;
    }

    @Bean("Course-07")
    public CourseList courseCreation07() {
        CourseList courseList = new CourseList("Software Requirement Analysis", "Design",
                "Main course contains 2 parts which cover SRS writing and working UML Diagrams. There are also additional series of thematic articles.");
        return courseList;
    }

    //Quizzes
    @Bean("Quiz-01")
    public Quizzes quizCreation01(){
        Quizzes quizzes = new Quizzes(20.0, "Quiz-01: Japanese Culture",15000, true);
        quizzes.setBatchesSet(batchCreation01());
        return quizzes;
    }

    @Bean("Quiz-02")
    public Quizzes quizCreation02(){
        Quizzes quizzes = new Quizzes(20.0, "Quiz-02: Japanese Culture",15000, true);
        quizzes.setBatchesSet(batchCreation02());
        return quizzes;
    }

    @Bean("Quiz-03")
    public Quizzes quizCreation03(){
        Quizzes quizzes = new Quizzes(25.0, "Quiz-01: Algorithm",25000, true);
        quizzes.setBatchesSet(batchCreation01());
        return quizzes;
    }
    @Bean("Quiz-04")
    public Quizzes quizCreation04(){
        Quizzes quizzes = new Quizzes(28.0, "Quiz-01: JavaScript",15000, true);
        quizzes.setBatchesSet(batchCreation03());
        return quizzes;
    }
    @Bean("Quiz-05")
    public Quizzes quizCreation05(){
        Quizzes quizzes = new Quizzes(20.0, "Quiz-01: JavaEE",15000, true);
        quizzes.setBatchesSet(batchCreation05());
        return quizzes;
    }

    //Trainer
    @Bean("Trainer-01")
    public TrainerProfile trainerProfileCreation01(){
        TrainerProfile trainerProfile = new TrainerProfile(courseCreation01(), adminRoleTest().getUserName(), "Nasim Hasan", "Dhaka",
                "016899587897", "nasim@gmail.com", 8.00, 14.00, adminRoleTest());
        return trainerProfile;
    }

    @Bean("Trainer-02")
    public TrainerProfile trainerProfileCreation02(){
        TrainerProfile trainerProfile = new TrainerProfile(courseCreation02(), adminRoleTest01().getUserName(), "Rafayet Hasan", "Dhaka",
                "016899587897", "rafa@gmail.com", 8.00, 14.00, adminRoleTest01());
        return trainerProfile;
    }

    @Bean("Trainer-03")
    public TrainerProfile trainerProfileCreation03(){
        TrainerProfile trainerProfile = new TrainerProfile(courseCreation03(), moderatorCredential01().getUserName(), "Rafiul Habib", "Khulna",
                "016899587897", "rafiul@gmail.com", 9.00, 13.00, moderatorCredential01());
        return trainerProfile;
    }

    @Bean("Trainer-04")
    public TrainerProfile trainerProfileCreation04(){
        TrainerProfile trainerProfile = new TrainerProfile(courseCreation03(), moderatorCredential02().getUserName(), "Rafiul Habib", "Khulna",
                "016899587897", "rafiul@gmail.com", 9.00, 13.00, moderatorCredential02());
        return trainerProfile;
    }

    //Trainee
    @Bean("Trainee-01")
    public TraineeProfile traineeProfileCreation01(){
        TraineeProfile traineeProfile = new TraineeProfile(userRoleTest().getUserName(), "Raktim Raihan", "Dhaka", "01689952206","raktim@gmail.com",userRoleTest(),batchCreation01());
        return traineeProfile;
    }

    @Bean("Trainee-02")
    public TraineeProfile traineeProfileCreation02(){
        TraineeProfile traineeProfile = new TraineeProfile(userRoleTest01().getUserName(), "Hasnat", "Dhaka", "0124585456","hasnat@gmail.com",userRoleTest01(),batchCreation02());
        return traineeProfile;
    }

    @Bean("Trainee-03")
    public TraineeProfile traineeProfileCreation03(){
        TraineeProfile traineeProfile = new TraineeProfile(userRoleTest03().getUserName(), "Hanjala Hasnat", "Dhaka", "0124585456","hanjala@gmail.com",userRoleTest03(),batchCreation03());
        return traineeProfile;
    }

    @Bean("Trainee-04")
    public TraineeProfile traineeProfileCreation04(){
        TraineeProfile traineeProfile = new TraineeProfile(userRoleTest04().getUserName(), "Humaira Ashfia", "Dhaka", "0124585456","humayra@gmail.com",userRoleTest04(),batchCreation04());
        return traineeProfile;
    }

    @Bean("Trainee-05")
    public TraineeProfile traineeProfileCreation05(){
        TraineeProfile traineeProfile = new TraineeProfile(userRoleTest05().getUserName(), "Nafia Sultana", "Dhaka", "0124585456","nafia@gmail.com",userRoleTest05(),batchCreation04());
        return traineeProfile;
    }

    //Assign Quiz Marks to Trainee
    @Bean("Quiz-Mark-01")
    public QuizMarksConnecting quizMarksConnecting01(){
        QuizMarksConnecting quizMarksConnecting = new QuizMarksConnecting(traineeProfileCreation01(), quizCreation01(), 24.0);
        quizMarksConnecting.setReviewer("M-01");
        quizMarksConnecting.setSubmitted(true);
        quizMarksConnecting.setReviewed(true);
        return quizMarksConnecting;
    }

    @Bean("Quiz-Mark-02")
    public QuizMarksConnecting quizMarksConnecting02(){
        QuizMarksConnecting quizMarksConnecting = new QuizMarksConnecting(traineeProfileCreation02(), quizCreation02(), 20.0);
        quizMarksConnecting.setReviewer("M-01");
        quizMarksConnecting.setSubmitted(true);
        quizMarksConnecting.setReviewed(true);
        return quizMarksConnecting;
    }

    @Bean("Quiz-Mark-03")
    public QuizMarksConnecting quizMarksConnecting03(){
        QuizMarksConnecting quizMarksConnecting = new QuizMarksConnecting(traineeProfileCreation03(), quizCreation03(), 0.0);

        return quizMarksConnecting;
    }

    @Bean("Quiz-Mark-04")
    public QuizMarksConnecting quizMarksConnecting04(){
        QuizMarksConnecting quizMarksConnecting = new QuizMarksConnecting(traineeProfileCreation04(), quizCreation04(), 0.0);

        return quizMarksConnecting;
    }

    @Bean("Quiz-Mark-05")
    public QuizMarksConnecting quizMarksConnecting05(){
        QuizMarksConnecting quizMarksConnecting = new QuizMarksConnecting(traineeProfileCreation05(), quizCreation05(), 0.0);


        return quizMarksConnecting;
    }

    @Bean("Batch-Course-Connect-01")
    public BatchCourseConnecting batchCourseConnecting01(){
        BatchCourseConnecting batchCourseConnecting = new BatchCourseConnecting(batchCreation01(), courseCreation01(), 14.30, 16.56);
        batchCourseConnecting.setTrainerName("M-01");
        batchCourseConnecting.setAssigned(true);
        return batchCourseConnecting;
    }

    @Bean("Batch-Course-Connect-02")
    public BatchCourseConnecting batchCourseConnecting02(){
        BatchCourseConnecting batchCourseConnecting = new BatchCourseConnecting(batchCreation01(), courseCreation02(), 13.30, 15.40);
        batchCourseConnecting.setTrainerName("M-02");
        batchCourseConnecting.setAssigned(true);
        return batchCourseConnecting;
    }

    @Bean("Batch-Course-Connect-03")
    public BatchCourseConnecting batchCourseConnecting03(){
        BatchCourseConnecting batchCourseConnecting = new BatchCourseConnecting(batchCreation01(), courseCreation03(), 10.30, 12.40);

        return batchCourseConnecting;
    }

    @Bean("Batch-Course-Connect-04")
    public BatchCourseConnecting batchCourseConnecting04(){
        BatchCourseConnecting batchCourseConnecting = new BatchCourseConnecting(batchCreation02(), courseCreation03(), 10.30, 12.40);

        return batchCourseConnecting;
    }

}