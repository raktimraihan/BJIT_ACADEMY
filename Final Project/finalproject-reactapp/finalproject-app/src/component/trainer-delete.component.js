
import Input from "react-validation/build/input";
import Form from "react-validation/build/form";
import Select from "react-validation/build/select";

import CheckButton from "react-validation/build/button";
import { useLocation, useNavigate, useParams } from "react-router";
import React, { Component, useEffect, useState } from "react";
import userService from "../service/user-service";

const TrainerDelete = () => {

  let { userNameParam } = useParams();
  let location = useLocation();

  const [loading, setStateLoading] = useState(false);
  const [checkBtn, setCheckButton] = useState();
  const [successfulFinal, setSuccessfulFinal] = useState();
  const [form, setForm] = useState();
  const [message, setMessage] = useState("");

  const [userName, setUserName] = useState(location.state.username);
  
  const navigate = useNavigate();

  function handleRegister(e) {
    
    setStateLoading(true);
    e.preventDefault();

    form.validateAll();

    if (checkBtn.context._errors.length === 0) {
      userService.deleteTrainer(userName).then(
        (response) => {
          setStateLoading(false);
          setMessage(response.data.message);
          setSuccessfulFinal(true);
        },
        (error) => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          setStateLoading(false);
          setSuccessfulFinal(false);
          setMessage(resMessage);
        }
      );
    }
  }

  return (
    <div>
      <div className="col-md-12">
        <div className="card card-container">
          <Form
            clas="align-center"
            onSubmit={handleRegister}
            ref={(c) => {
              setForm(c);
            }}
          >
            {!successfulFinal && (
              <div className="form-group col-md-10 px-4">
                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">User Name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="userName"
                    value={userName}
                    disabled
                  />
                </div>

                <div className="form-group col-md-4 px-4">
                  <button
                    className="btn btn-primary btn-block px-4 mt-4"
                    disabled={loading}
                  >
                    {loading && (
                      <span className="spinner-border spinner-border-sm"></span>
                    )}
                    <span>Delete</span>
                  </button>
                </div>
              </div>
            )}

            {message &&  (
              <div className="form-group">
                <div className={successfulFinal? "alert alert-success":"alert alert-danger"} role="alert">
                  {message}
                </div>
              </div>
            )}

            {successfulFinal &&
              (
              window.setTimeout(function () {
                //window.location.reload();
              }, 3000),
              (
                
                <></>
              ))}

            <CheckButton
              style={{ display: "none" }}
              ref={(c) => {
                setCheckButton(c);
              }}
            />
          </Form>
        </div>
      </div>
    </div>
  );
};

export default TrainerDelete;