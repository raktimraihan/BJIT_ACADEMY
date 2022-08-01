import React, { useEffect, useState } from "react";
import userService from "../service/user-service";
import { Route, Routes } from "react-router";
import authService from "../service/auth-service";
import TableBodyQuizNotReviewed from "../component/quiz-no-reviewed-body.component";
import TableBodyQuizSubmission from "./table-bosy-submission.component";

const QuizSubmissionUser = () => {
  const [quizInfo, setQuizInfo] = useState();
  const[userName, setUSerName] = useState(authService.getCurrentUser().userName)

  useEffect(() => {
    userService
      ?.getAllQuizDataNotSubmitted(userName)
      .then((result) => setQuizInfo(result.data));
  }, []);

  return (
    
    <div>
      <table class="table col-md-12 table-striped table-dark table-bordered">
        <thead class="thead-dark">
          <tr>
            <th scope="col">#</th>
            <th scope="col">Quiz Title</th>
            <th scope="col">Batch Name</th>
            <th scope="col">Trainee UserName</th>
            <th scope="col">Trainee Name</th>
            <th scope="col">Total Marks</th>
            
            
          </tr>
        </thead>
        <tbody>
          {quizInfo?.map((post, index) => (
            <TableBodyQuizSubmission index={index} post={post} />
          ))}
        </tbody>
      </table>

      <div>
      
      </div>
    </div>
    
  );
};

export default QuizSubmissionUser;
