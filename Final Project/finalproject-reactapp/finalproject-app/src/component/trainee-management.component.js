import React, { useEffect, useState } from "react";
import userService from "../service/user-service";
import TableBodyTrainerDataAll from "./subcomponent/table-response-trainer.component";
import { Route, Routes } from "react-router";
import TableBodyTraineeDateaAll from "./table-body-trainee-data-all.component";

const TraineeManagement = () => {
  const [traineeInfo, setTraineeInfo] = useState();

  useEffect(() => {
    userService
      ?.getAllTraineeData()
      .then((result) => setTraineeInfo(result.data));
  }, []);

  return (
    
    <div>
      <table class="table col-md-12 table-striped table-dark table-bordered">
        <thead class="thead-dark">
          <tr>
            <th scope="col">#</th>
            <th scope="col">User Name</th>
            <th scope="col">Name</th>
            <th scope="col">Role</th>
            <th scope="col">Password</th>
            <th scope="col">Email</th>
            <th scope="col">Phone</th>
            <th scope="col">Address</th>
            <th scope="col">Batch Name</th>
            <th scope="col">Assigned Course</th>
            <th scope="col">Reviewed Quizzes</th>
            <th scope="col">Assigned Quizzes</th>
          </tr>
        </thead>
        <tbody>
          {traineeInfo?.map((post, index) => (
            <TableBodyTraineeDateaAll index={index} post={post} />
          ))}
        </tbody>
      </table>

      <div>
      
      </div>
    </div>
    
  );
};

export default TraineeManagement;
