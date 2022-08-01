import { Button } from "bootstrap";
import React from "react";
import { Link, Outlet, useNavigate } from "react-router-dom";
import PrintRowWithInRow from "./table-data-trainer.component";
import { Route, Routes, useRouteMatch } from "react-router-dom";
import TrainerEdit from "../trainer-edit.component";

const TableBatchRespDataAll = ({
  post: {
    batchName,
    courseName,
    startTime,
    endTime,
    activeStatus,
    trainerName
  },
  index,
}) => {
  let assigned = [];
  let quizAssigned = [];
  const navigate = useNavigate();

  return (
    <>
      <tr>
        <th scope="row">{index + 1}</th>
        <td>{batchName}</td>
        <td>{courseName}</td>
        <td>{startTime}</td>
        <td>{endTime}</td>
        <td>{activeStatus}</td>
        <td>{trainerName}</td>

        {/* <td>
          <div class="dropdown">
            <button
              class="btn btn-secondary dropdown-toggle"
              type="button"
              id="dropdownMenuButton"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Action
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
              
              <a class="dropdown-item" onClick={() => navigate("/remove-course-from-batch", 
              {state:{batchName: batchName, courseName: courseName, startTime: startTime, endTime: endTime, activeStatus: activeStatus}})}>
                Remove this Course
              </a>
              <a class="dropdown-item" onClick={() => navigate("/delete", 
              {state:{username: userName}})}>
                Delete this entry
              </a>
              <a class="dropdown-item" onClick={() => navigate("/new-course-assign-trainer", 
              {state:{username: userName}})}>
                Assign New Course
              </a>

              <a class="dropdown-item" onClick={() => navigate("/remove-course-assign-trainer", 
              {state:{username: userName}})}>
                Remove from Course
              </a>
            </div>
          </div>
        </td> */}
        <div></div>
      </tr>

      <Outlet />
    </>
  );
};

export default TableBatchRespDataAll;
