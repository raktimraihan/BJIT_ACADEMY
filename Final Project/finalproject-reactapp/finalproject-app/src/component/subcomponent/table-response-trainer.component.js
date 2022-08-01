import { Button } from "bootstrap";
import React from "react";
import { Link, Outlet, useNavigate } from "react-router-dom";
import PrintRowWithInRow from "./table-data-trainer.component";
import { Route, Routes, useRouteMatch } from "react-router-dom";
import TrainerEdit from "../trainer-edit.component";

const TableBodyTrainerDataAll = ({
  post: {
    userName,
    role,
    address,
    phone,
    email,
    password,
    assignedCourse,
    reviewedQuiz,
    name,
  },
  index,
}) => {
  let assigned = [];
  let quizAssigned = [];
  const navigate = useNavigate();

  const NavigateEdit = () => {
    console.log("hello");
    navigate("/", { push: true });
  };

  if (assignedCourse.length < 3) {
    assignedCourse = "No Course Assigned";
  } else {
    assignedCourse = assignedCourse.substring(1, assignedCourse.length - 1);
    assigned = assignedCourse.split(",");
    console.log(assigned);
  }

  if (reviewedQuiz.length < 3) {
    reviewedQuiz = "No Course Assigned";
  } else {
    reviewedQuiz = reviewedQuiz.substring(1, reviewedQuiz.length - 1);
    //quizAssigned = reviewedQuiz.split(",");
    console.log(quizAssigned);
  }

  return (
    <>
      <tr>
        <th scope="row">{index + 1}</th>
        <td>{userName}</td>
        <td>{name}</td>
        <td>{role}</td>
        <td>Can't be Displayed</td>
        <td>{email}</td>
        <td>{phone}</td>
        <td>{address}</td>

        <td>
          <PrintRowWithInRow userData={assignedCourse} />
        </td>

        <td>
          <PrintRowWithInRow userData={reviewedQuiz} />
        </td>

        <td>
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
              
              <a class="dropdown-item" onClick={() => navigate("/edit", 
              {state:{name: name, username: userName, role:role, email:email, phone:phone, address:address, password: password}})}>
                Update Info
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
        </td>
        <div></div>
      </tr>

      <Outlet />
    </>
  );
};

export default TableBodyTrainerDataAll;
