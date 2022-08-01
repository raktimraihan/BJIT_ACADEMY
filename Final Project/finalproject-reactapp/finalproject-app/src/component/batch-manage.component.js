import React, { useEffect, useState } from "react";
import userService from "../service/user-service";
import TableBodyTrainerDataAll from "./subcomponent/table-response-trainer.component";
import { Route, Routes } from "react-router";
import TrainerEdit from "./trainer-edit.component";
import TableBatchRespDataAll from "./subcomponent/table-response-batch-component";

const BatchManagementViewAll = () => {
  const [trainerInfo, setTrainerInfo] = useState();

  useEffect(() => {
    userService
      ?.getAllBatchDataDetails()
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
            <th scope="col">Start Time</th>
            <th scope="col">End Time</th>
            <th scope="col">Trainer Name</th>
            <th scope="col">Batch Status </th>
            
          </tr>
        </thead>
        <tbody>
          {trainerInfo?.map((post, index) => (
            <TableBatchRespDataAll index={index} post={post} />
          ))}
        </tbody>
      </table>

      <div>
      
      </div>
    </div>
    
  );
};

export default BatchManagementViewAll;
