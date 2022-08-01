
import Input from "react-validation/build/input";
import Form from "react-validation/build/form";
import Select from "react-validation/build/select";

import CheckButton from "react-validation/build/button";
import { useLocation, useNavigate, useParams } from "react-router";
import React, { Component, useEffect, useState } from "react";
import userService from "../service/user-service";
import OptionMapperData from "./subcomponent/optio-mapper-data";
import OptionMapper from "./subcomponent/option.component";

const TraineeBatchAssign = () => {

  let { userNameParam } = useParams();
  let location = useLocation();

  const [loading, setStateLoading] = useState(false);
  const [checkBtn, setCheckButton] = useState();
  const [successfulFinal, setSuccessfulFinal] = useState();
  const [form, setForm] = useState();
  const [message, setMessage] = useState("");

  const [userName, setUserName] = useState(location.state.username);
  const [courseList, setCourseList] = useState();
  const [result, setResult] = useState();

  useEffect(() =>{
    userService?.getBatchesNamesOnly().then((response) => setResult(response.data));
  },[]);
  
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

  function onChangeCourseList(e){
    setCourseList(e.target.value)
  }

  function handleRegister(e) {
    
    setStateLoading(false);
    e.preventDefault();

    form.validateAll();
    
    if (checkBtn.context._errors.length === 0) {
      setStateLoading(true);
      userService.assignTrainerToNewBatch(userName, courseList).then(
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
                  <label htmlFor="courseName">User Name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="userName"
                    value={userName}
                    disabled
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Course List</label>
                  <Select
                    type="text"
                    className="form-control"
                    name="courseList"
                    value={courseList}
                    onChange={onChangeCourseList}
                    validations={[required]}
                  >
                    <option value="">Choose Batch to Assign</option>
                    <OptionMapper axiosMethod={userService.getBatchesNamesOnly()} />
                    {/* {console.log(result)}
                    {result?.map((post) => (
                        <>
                        <OptionMapperData post={post}/>
                        </>
                    ))}; */}
                  </Select>
                </div>

                <div className="form-group col-md-4 px-4">
                  <button
                    className="btn btn-primary btn-block px-4 mt-4"
                    disabled={loading}
                  >
                    {loading && (
                      <span className="spinner-border spinner-border-sm"></span>
                    )}
                    <span>Assign</span>
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
                navigate("/manage-trainee")
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

export default TraineeBatchAssign;