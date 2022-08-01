import React, { useEffect, useState } from "react";
import userService from "../service/user-service";
import TableBodyTrainerDataAll from "./subcomponent/table-response-trainer.component";
import { Route, Routes } from "react-router";
import TableBodyTraineeDateaAll from "./table-body-trainee-data-all.component";
import TableBodyQuizNotReviewed from "./quiz-no-reviewed-body.component";

const QuizViewReviewed = () => {
  const [quizInfo, setQuizInfo] = useState();

  useEffect(() => {
    userService
      ?.getAllQuizDataReviewed()
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
            <th scope="col">Attained Marks</th>
            <th scope="col">Reviewer Username</th>
            <th scope="col">Reviewer Name</th>
            
          </tr>
        </thead>
        <tbody>
          {quizInfo?.map((post, index) => (
            <TableBodyQuizNotReviewed index={index} post={post} />
          ))}
        </tbody>
      </table>

      <div>
      
      </div>
    </div>
    
  );
};

export default QuizViewReviewed;
