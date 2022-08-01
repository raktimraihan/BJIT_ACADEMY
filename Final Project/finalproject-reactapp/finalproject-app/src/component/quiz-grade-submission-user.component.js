
import Input from "react-validation/build/input";
import Form from "react-validation/build/form";
import Select from "react-validation/build/select";
import TextArea from "react-validation/build/textarea";

import CheckButton from "react-validation/build/button";
import { useLocation, useNavigate, useParams } from "react-router";
import React, { Component, useEffect, useState } from "react";
import userService from "../service/user-service";
import OptionMapperData from "./subcomponent/optio-mapper-data";
import OptionMapper from "./subcomponent/option.component";
import authService from "../service/auth-service";

const QuizSubmissionUserForm = () => {

  let { userNameParam } = useParams();
  let location = useLocation();

  const userName = authService.getCurrentUser().userName;

  const [loading, setStateLoading] = useState(false);
  const [checkBtn, setCheckButton] = useState();
  const [successfulFinal, setSuccessfulFinal] = useState();
  const [form, setForm] = useState();
  const [message, setMessage] = useState("");
  const [result, setResult] = useState();
  const [reviewerName, setReviewerName] = useState();

  useEffect(() =>{
    userService?.getUserDetails(userName).then((response) => setReviewerName(response.data.name));
  },[]);

  console.log(result);

//   title: title,
//   batchName: batchName,
//   traineeUserName: traineeUserName,
//   traineeName: traineeName,
//   totalMarks: totalMarks,
//   attainedMarks: attainedMarks,
//   reviewerUserName: reviewerUserName,
//   reviewerName: reviewerUserName

  const [title, setTitle] = useState(location.state.title);
  const [batchName, setBatchName] = useState(location.state.batchName);
  const [traineeUserName, setTraineeUserName] = useState(location.state.traineeUserName);
  const [traineeName, setTraineeName] = useState(location.state.traineeName);
  const [reviewerUserName, setReviewerUserName] = useState(userName);
  
  const [totalMarks, setTotalMarks] = useState(location.state.totalMarks);
  const [attainedMarks, setAttainedMarks] = useState(location.state.attainedMarks);

  
  const navigate = useNavigate();

  const required = (value) => {
    if (!value) {
      return (
        <div className="alert alert-danger" role="alert">
          This field is required!
        </div>
      );
    }
  };

  function onChangeTitle(e){
    setTitle(e.target.value)
  }

  function onChangeBatchName(e){
    setBatchName(e.target.value)
  }

  function onChangeTraineeUserName(e){
    setTraineeUserName(e.target.value)
  }

  function onChangeTraineeName(e){
    setTraineeName(e.target.value)
  }

  function onChangeReviewUserName(e){
    setReviewerUserName(e.target.value)
  }

  function onChangeReviewerName(e){
    setReviewerName(e.target.value)
  }

  function onChangeTotalMarks(e){
    setTotalMarks(e.target.value)
  }

  function onChangeAttainedMarks(e){
    setAttainedMarks(e.target.value)
  }

  function handleRegister(e) {
    
    setStateLoading(false);
    e.preventDefault();

    form.validateAll();
    
    if (checkBtn.context._errors.length === 0) {

      setStateLoading(true);

      userService.assignCourseGradingSubmission(
            title,
            batchName,
            traineeUserName,
            traineeName,
            totalMarks,

      ).then(
        (response) => {
          setStateLoading(true);
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
                  <label htmlFor="courseName">Title</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="title"
                    value={title}
                    onChange={onChangeTitle}
                    validations={[required]}
                    disabled
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Batch Name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="batchName"
                    value={batchName}
                    onChange={onChangeBatchName}
                    validations={[required]}
                    disabled
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Trainee UserName</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="traineeUserName"
                    value={traineeUserName}
                    onChange={onChangeTraineeUserName}
                    validations={[required]}
                    disabled
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Trainee Name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="traineeName"
                    value={traineeName}
                    onChange={onChangeTraineeName}
                    validations={[required]}
                    disabled
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Total Marks</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="totalMarks"
                    value={totalMarks}
                    onChange={onChangeTotalMarks}
                    validations={[required]}
                    
                  />
                </div>

                <div className="form-group mt-4 px-4 col-md-12">
                  <label htmlFor="courseName">Answer</label>
                  <TextArea
                    type="textarea"
                    classNames="form-control"
                    name="attainedMarks"
                    
                    validations={[required]}
                    
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
                    <span>Submit</span>
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
                navigate("/quiz-submission")
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

export default QuizSubmissionUserForm;