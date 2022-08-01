import axios from "axios";
import AuthHeader from "./auth-header";
import authService from "./auth-service";

const API_URL = "http://localhost:8080/";

class UserService {
  getHelloAdmin() {
    return axios.get(API_URL + "helloA", { headers: AuthHeader() });
  }

  registerBatch(batchName, active) {
    return axios.post(
      API_URL + "batch/batch-register",
      {
        batchName,
        active,
      },
      { headers: AuthHeader() }
    );
  }

  registerCourse(courseName, topic, description) {
    return axios.post(
      API_URL + "course/course-register",
      {
        courseName,
        topic,
        description,
      },
      { headers: AuthHeader() }
    );
  }

  registerTrainee(userName, name, email, address, phone, password, role, batch) {
    return axios.post(
        API_URL + "trainee/trainee-register",
        {           
            userName,
            name,
            email,
            address,
            phone,
            password,
            role,
            batch
        },
        {headers: AuthHeader()}
    );
  }

  getBatchesNamesOnly(){
    return axios.get(API_URL + "batch/batch-names", {headers: AuthHeader()})
  }

  getCourseNames(){
    return axios.get(API_URL + "course/course-names", {headers: AuthHeader()})
  }

  getCourseNameAsPerBatch(batchName){
    return axios.post(API_URL + "course/course-list-for-batch", {batchName},{headers: AuthHeader()})
  }

  insertCourseBatchConnecting(courseName, batchName, startHour, endHour){
    return axios.post(API_URL + "batch/batch-connecting-insertion", {
      courseName, 
      batchName, 
      startHour, 
      endHour},
      {headers: AuthHeader()})
  }

  registerQuiz(totalMarks, title, duration, active) {
    return axios.post(
        API_URL + "quiz/quiz-register",
        {           
          totalMarks, title, duration, active
        },
        {headers: AuthHeader()}
    );
  }

  getQuizNameAsPerBatch(batchName){
    return axios.post(API_URL + "quiz/quiz-list-per-batch", {batchName},{headers: AuthHeader()})
  }

  insertQuizBatchConnecting(batchName, quizName){
    return axios.post(
      API_URL + "quiz/quiz-batch-register",
      {           
        batchName,
        quizName
      },
      {headers: AuthHeader()}
  );
  }

  getBatchesSchedule(){
    return axios.get(API_URL + "batch/batches-schedule", {headers: AuthHeader()})
  }

  registerTrainer(userName, name, email, address, phone, password, role, course) {
    return axios.post(
        API_URL + "trainer/trainer-registration",
        {           
            userName,
            name,
            email,
            address,
            phone,
            password,
            role,
            course
        },
        {headers: AuthHeader()}
    );
  }

  getAllTrainerData(){
    return axios.get(API_URL+ "trainer/get-all-trainer-data", {headers: AuthHeader()});
  }

  updateTrainer(userName, name, password, address){
    return axios.post(
      API_URL + "trainer/update-trainer", {userName, name, password, address}, {header: AuthHeader()}
    )
  }

  deleteTrainer(userName){
    return axios.post(
      API_URL + "trainer/trainer-delete", {userName}, {header: AuthHeader()}
    )
  }

  chceckSchedule(userName){
    return axios.post(
      API_URL + "course/check-schedule", {userName}, {headers: AuthHeader()}
    )
  }

  assignTrainerToNewCourse(userName, courseList){
    return axios.post(
      API_URL + "trainer/assign-new-course", {userName, courseList}, {headers: AuthHeader()}
    )
  }

  chceckScheduleMatching(userName){
    return axios.post(
      API_URL + "course/check-schedule-matching", {userName}, {headers: AuthHeader()}
    )
  }

  removeTrainerToNewCourse(userName, courseList){
    return axios.post(
      API_URL + "trainer/remove-new-course", {userName, courseList}, {headers: AuthHeader()}
    )
  }

  getAllTraineeData(){
    return axios.get(API_URL+ "trainee/get-all-trainee-data", {headers: AuthHeader()});
  }

  updateTrainee(userName, name, password, address){
    return axios.post(
      API_URL + "trainee/update-trainee", {userName, name, password, address}, {header: AuthHeader()}
    )
  }

  deleteTrainee(userName){
    return axios.post(
      API_URL + "trainee/trainee-delete", {userName}, {header: AuthHeader()}
    )
  }

  assignTrainerToNewBatch(userName, courseList){
    return axios.post(
      API_URL + "trainee/assign-new-batch", {userName, courseList}, {headers: AuthHeader()}
    )
  }

  getAllQuizDataNotReviewed(){
    return axios.get(
      API_URL + "quiz/get-all-quiz-not-reviewed", {headers: AuthHeader()}
    )
  }

  assignCourseGrading(
    title,
    batchName,
    traineeUserName,
    traineeName,
    totalMarks,
    attainedMarks,
    reviewerUserName,
    reviewerName,

){
  return(
    axios.post(
      API_URL + "quiz/assign-quiz-grading",{
          title,
          batchName,
          traineeUserName,
          traineeName,
          totalMarks,
          attainedMarks,
          reviewerUserName,
          reviewerName,
      }, {headers: AuthHeader()}
    )
  )
}

getUserDetails(userName){
  return axios.post(
    API_URL + "get-user-name",{userName},{headers: AuthHeader()}
  )
}

getAllQuizDataReviewed(){
  return axios.get(
    API_URL + "quiz/get-all-quiz-reviewed", {headers: AuthHeader()}
  )
}

getTrainerSchedule(userName){
  return axios.post(
    API_URL + "trainer/trainer-schedule",{userName},{headers: AuthHeader()}
  )
}

getTraineeSchedule(userName){
  return axios.post(
    API_URL + "trainee/trainee-schedule",{userName},{headers: AuthHeader()}
  )
}

getAllQuizDataNotSubmitted(userName){
  return axios.post(
    API_URL + "quiz/get-user-per-submission", {userName}, {headers: AuthHeader()}
  )
}

assignCourseGradingSubmission(
  title,
  batchName,
  traineeUserName,
  traineeName,
  totalMarks,

){
  return axios.post(
    API_URL + "quiz/quiz-assign-submission",{
      title,
      batchName,
      traineeUserName,
      traineeName,
      totalMarks,
    },{header: AuthHeader()}
  )
}

getTraineeResult(userName){
  return axios.post(
    API_URL + "quiz/get-result-user",{userName},{headers: AuthHeader()}
  )
}

getAllQuizDataSubmitted(){
  return axios.get(
    API_URL + "quiz/get-all-quiz-submitted", {headers: AuthHeader()}
  )
}

getAllBatchDataDetails(){
  return axios.post(API_URL+ "batch/get-batch-details", {headers: AuthHeader()});
}

deleteBatchCourse(batchName, courseName){
  return axios.post(API_URL + "batch/delete-batch-course", {batchName, courseName}, {headers: AuthHeader()})
}

}

export default new UserService();
