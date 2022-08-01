import Input from "react-validation/build/input";
import { Button } from "bootstrap";
import React from "react";
import { Link, Outlet, useNavigate } from "react-router-dom";
import { Route, Routes, useRouteMatch } from "react-router-dom";
import PrintRowWithInRow from "./subcomponent/table-data-trainer.component";
import { useState } from "react";

const TableBodyQuizReviewed = ({
  post: {
    title,
    batchName,
    traineeUserName,
    traineeName,
    totalMarks,
    attainedMarks,
    reviewerUserName,
    reviewerName,
  },
  index,
}) => {
  let assigned = [];
  let quizAssigned = [];
  const navigate = useNavigate();
  const [grade, setGrade] = useState();

  const required = (value) => {
    if (!value) {
      return (
        <div className="alert alert-danger" role="alert">
          This field is required!
        </div>
      );
    }
  };

  function handleOnClick(){
    console.log()
  }

  return (
    <>
      <tr>
        <th scope="row">{index + 1}</th>
        <td>{title}</td>
        <td>{batchName}</td>
        <td>{traineeUserName}</td>
        <td>{traineeName}</td>
        <td>{totalMarks}</td>
        <td>{attainedMarks}</td>
        <td>{reviewerUserName}</td>
        <td>{reviewerName}</td>

        
        <div></div>
      </tr>

      <Outlet />
    </>
  );
};

export default TableBodyQuizReviewed;
