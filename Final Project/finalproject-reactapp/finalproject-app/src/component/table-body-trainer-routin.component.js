import { Button } from "bootstrap";
import React from "react";
import { Link, Outlet, useNavigate } from "react-router-dom";
import { Route, Routes, useRouteMatch } from "react-router-dom";


const TableBodyTrainerRoutin = ({
  post: {
    batchName,
    courseName,
    startTime,
    endTime
  },
  index,
}) => {

  const navigate = useNavigate();

  return (
    <>
      <tr>
        <th scope="row">{index + 1}</th>
        <td>{batchName}</td>
        <td>{courseName}</td>
        <td>{startTime}</td>
        <td>{endTime}</td>

        <div></div>
      </tr>

      <Outlet />
    </>
  );
};

export default TableBodyTrainerRoutin;
