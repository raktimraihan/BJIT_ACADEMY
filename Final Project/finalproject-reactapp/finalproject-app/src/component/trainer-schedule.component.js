import React, { useEffect, useState } from "react";
import userService from "../service/user-service";
import TableBodyTrainerDataAll from "./subcomponent/table-response-trainer.component";
import { Route, Routes } from "react-router";
import TrainerEdit from "./trainer-edit.component";
import authService from "../service/auth-service";
import TableBodyTrainerRoutin from "./table-body-trainer-routin.component";

const TrainerClassRoutin = () => {
  const [trainerInfo, setTrainerInfo] = useState();
  const [userName, setUserName] = useState(authService.getCurrentUser().userName);
  useEffect(() => {
    userService
      ?.getTrainerSchedule(userName)
      .then((result) => setTrainerInfo(result.data));
  }, []);

  return (
    
    <div>
      <table class="table col-md-12 table-striped table-dark table-bordered">
        <thead class="thead-dark">
          <tr>
            <th scope="col">#</th>
            <th scope="col">Batch Name</th>
            <th scope="col">Course Name</th>
            <th scope="col">Start Timing</th>
            <th scope="col">End Timing</th>
          </tr>
        </thead>
        <tbody>
          {trainerInfo?.map((post, index) => (
            <TableBodyTrainerRoutin index={index} post={post} />
          ))}
        </tbody>
      </table>

      <div>
      
      </div>
    </div>
    
  );
};

export default TrainerClassRoutin;
