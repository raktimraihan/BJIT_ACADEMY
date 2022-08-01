import Input from "react-validation/build/input";
import Form from "react-validation/build/form";
import Select from "react-validation/build/select";

import CheckButton from "react-validation/build/button";
import { useLocation, useNavigate, useParams } from "react-router";
import React, { Component, useEffect, useState } from "react";
import userService from "../service/user-service";

const TrainerEdit = () => {
  let { userNameParam } = useParams();
  let location = useLocation();

  const [loading, setStateLoading] = useState(false);
  const [checkBtn, setCheckButton] = useState();
  const [successfulFinal, setSuccessfulFinal] = useState();
  const [form, setForm] = useState();
  const [message, setMessage] = useState("");

  const [userName, setUserName] = useState(location.state.username);
  const [name, setName] = useState(location.state.name);
  const [phone, setPhone] = useState(location.state.phone);
  const [email, setEmail] = useState(location.state.email);
  const [address, setAddress] = useState(location.state.address);
  const [role, setRole] = useState(location.state.role);
  const [password, setPassword] = useState();
  

  const navigate = useNavigate();

  const [confirmPassword, setConfirmPassword] = useState(
    location.state.password
  );

  const required = (value) => {
    if (!value) {
      return (
        <div className="alert alert-danger" role="alert">
          This field is required!
        </div>
      );
    }
  };

  const required1 = (value) => {
    if (!value) {
      return (
        <div className="alert alert-danger" role="alert">
          This field is required!
        </div>
      );
    }
  };

  function requiredConfirmPass(value, value2) {
    if (value === value2) return true;
    else return false;
  }

  function onChangeUserName(e) {
    setUserName(e.target.value);
    console.log("User Name " + userName);
  }

  function onChangeName(e) {
    setName(e.target.value);
  }

  function changeRole(e) {
    setRole(e.target.value);
  }

  function changePhone(e) {
    setPhone(e.target.value);
  }

  function changeEmail(e) {
    setEmail(e.target.value);
  }

  function OnChangePassword(e) {
    setPassword(e.target.value);
    console.log(e.target.value);
  }

  function changeAddress(e) {
    setAddress(e.target.value);
  }

  function changeConfirmPassword(e) {
    setConfirmPassword(e.target.value);
  }

  function handleRegister(e) {
    console.log(
      checkBtn.context._errors.length +
        " " +
        requiredConfirmPass(password, confirmPassword)
    );
    //setSuccessfulFinal(true);
    setStateLoading(true);
    e.preventDefault();

    form.validateAll();

    if (!requiredConfirmPass(password, confirmPassword)) {
      const resMessage = "Password Don't match";
      setStateLoading(false);
      setSuccessfulFinal(false);
      setMessage(resMessage);
    }

    if (
      requiredConfirmPass(password, confirmPassword) &&
      checkBtn.context._errors.length === 0
    ) {
      userService.updateTrainer(userName, name, password, address).then(
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
                    onChange={onChangeUserName}
                    validations={[required]}
                    disabled
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Name</label>
                  <Input
                    type="text"
                    className="form-control col-md-12"
                    name="name"
                    value={name}
                    onChange={onChangeName}
                    validations={[required1]}
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Password</label>
                  <Input
                    type="text"
                    className="form-control col-md-12"
                    name="password"
                    value={password}
                    onChange={OnChangePassword}
                    validations={[required]}
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Confirm Password</label>
                  <Input
                    type="text"
                    className="form-control col-md-12"
                    name="password"
                    value={confirmPassword}
                    onChange={changeConfirmPassword}
                    validations={[required]}
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Phone</label>
                  <Input
                    type="text"
                    className="form-control col-md-12"
                    name="name"
                    value={phone}
                    onChange={changePhone}
                    validations={[required]}
                    disabled
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Email</label>
                  <Input
                    type="text"
                    className="form-control col-md-12"
                    name="name"
                    value={email}
                    onChange={changeEmail}
                    validations={[required]}
                    disabled
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Address</label>
                  <Input
                    type="text"
                    className="form-control col-md-12"
                    name="name"
                    value={address}
                    onChange={changeAddress}
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
                    <span>Register</span>
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
                navigate("/manage-trainers")
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

export default TrainerEdit;
