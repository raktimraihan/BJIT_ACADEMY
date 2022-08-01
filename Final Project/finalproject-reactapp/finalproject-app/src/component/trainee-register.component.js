import Input from "react-validation/build/input";
import Form from "react-validation/build/form";
import Select from "react-validation/build/select";
import CheckButton from "react-validation/build/button";
import { isEmail, isPhone } from "validator";
import React, { Component, useEffect, useState } from "react";
import userService from "../service/user-service";
import OptionMapper from "./subcomponent/option.component";



const required = (value) => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const emailCheck = (value) => {
  if (!isEmail(value)) {
    return (
      <div className="alert alert-danger" role="alert">
        This is not a valid email.
      </div>
    );
  }
};

const phoneCheck = (value) => {
  if (value.length < 11 || value.length > 15) {
    return (
      <div className="alert alert-danger" role="alert">
        This is not a Mobile Number.
      </div>
    );
  }
};

const userNameCheck = (value) => {
  if (value.length < 3 || value.length > 5) {
    return (
      <div className="alert alert-danger" role="alert">
        The username must be between 3 and 5 characters.
      </div>
    );
  }
};

export default class TraineeRegistration extends Component {
  constructor(props) {
    super(props);

    this.handleRegister = this.handleRegister.bind(this);
    this.onChangeUserName = this.onChangeUserName.bind(this);
    this.onChangeName = this.onChangeName.bind(this);
    this.onChangeEmail = this.onChangeEmail.bind(this);
    this.onChangeAddress = this.onChangeAddress.bind(this);
    this.onChangePhone = this.onChangePhone.bind(this);
    this.onChangePassword = this.onChangePassword.bind(this);
    this.onChangeRole = this.onChangeRole.bind(this);
    this.onChangeBatch = this.onChangeBatch.bind(this);
    this.getBatchesName = this.getBatchesName.bind(this);

    this.state = {
      userName: "",
      name: "",
      email: "",
      address: "",
      phone: "",
      password: "",
      role: "",
      batch: "",
      batchOption: [],
      successful: false,
      message: "",
    };

    
  }

  getBatchesName() {
    userService
      .getBatchesNamesOnly()
      .then((result) => this.setState({ batchOption: result.data }));
    //console.log(this.state.batchOption);
  }
  onChangeUserName(e) {
    this.setState({
      userName: e.target.value,
    });
  }

  onChangeName(e) {
    this.setState({
      name: e.target.value,
    });
  }

  onChangeEmail(e) {
    this.setState({
      email: e.target.value,
    });
  }

  onChangeAddress(e) {
    this.setState({
      address: e.target.value,
    });
  }

  onChangePhone(e) {
    this.setState({
      phone: e.target.value,
    });
  }

  onChangePassword(e) {
    this.setState({
      password: e.target.value,
    });
  }

  onChangeRole(e) {
    this.setState({
      role: e.target.value,
    });
  }

  onChangeBatch(e) {
    this.setState({
      batch: e.target.value,
    });
  }

  handleRegister(e) {
    e.preventDefault();

    this.setState({
      message: "",
      successful: false,
    });

    this.form.validateAll();

    if (this.checkBtn.context._errors.length === 0) {
      //console.log(this.state.courseName+" "+this.state.topic+" "+this.state.description);
      userService
        .registerTrainee(
          this.state.userName,
          this.state.name,
          this.state.email,
          this.state.address,
          this.state.phone,
          this.state.password,
          this.state.role,
          this.state.batch
        )
        .then(
          (response) => {
            this.setState({
              message: response.data.message,
              successful: true,
            });
          },
          (error) => {
            const resMessage =
              (error.response &&
                error.response.data &&
                error.response.data.message) ||
              error.message ||
              error.toString();

            this.setState({
              successful: false,
              message: resMessage,
            });
          }
        );
    }
  }

  render() {
    
    return (
      <div className="col-md-12">
        <div className="card card-container">
            
          <Form
            clas="align-center"
            onSubmit={this.handleRegister}
            ref={(c) => {
              this.form = c;
            }}
          >
            {!this.state.successful && (
              <div>
                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">User Name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="userName"
                    value={this.state.userName}
                    onChange={this.onChangeUserName}
                    validations={[required, userNameCheck]}
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="name"
                    value={this.state.name}
                    onChange={this.onChangeName}
                    validations={[required]}
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Email</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="email"
                    value={this.state.email}
                    onChange={this.onChangeEmail}
                    validations={[required, emailCheck]}
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Address</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="address"
                    value={this.state.address}
                    onChange={this.onChangeAddress}
                    validations={[required]}
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Phone</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="phone"
                    value={this.state.phone}
                    onChange={this.onChangePhone}
                    validations={[required, phoneCheck]}
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Password</label>
                  <Input
                    type="password"
                    className="form-control"
                    name="password"
                    value={this.state.password}
                    onChange={this.onChangePassword}
                    validations={[required]}
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Role</label>
                  <Select
                    name="role"
                    value={this.state.role}
                    onChange={this.onChangeRole}
                    validations={[required]}
                    className="form-control"
                  >
                    <option value="">Choose your Role</option>
                    <option value="ROLE_USER">ROLE_USER</option>
                    <option value="ROLE_MODERATOR">ROLE_MODERATOR</option>
                  </Select>
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Batch</label>
                  <Select
                    name="role"
                    value={this.state.batch}
                    onChange={this.onChangeBatch}
                    validations={[required]}
                    className="form-control"
                  >
                    <option value="">Choose Designated Batch</option>
                      <OptionMapper axiosMethod={userService.getBatchesNamesOnly()} />                    

                  </Select>

                </div>

                <div className="form-group col-md-4 px-4">
                  <button
                    className="btn btn-primary btn-block"
                    disabled={this.state.loading}
                  >
                    {this.state.loading && (
                      <span className="spinner-border spinner-border-sm"></span>
                    )}
                    <span>Register</span>
                  </button>
                </div>
              </div>
            )}

            {this.state.message && (
              <div className="form-group">
                <div
                  className={
                    this.state.successful
                      ? "alert alert-success"
                      : "alert alert-danger"
                  }
                  role="alert"
                >
                  {this.state.message}
                </div>
              </div>
            )}

            {this.state.successful &&
              ((this.setState.successful = false),
              window.setTimeout(function () {
                window.location.reload();
              }, 3000),
              (<></>))}
            <CheckButton
              style={{ display: "none" }}
              ref={(c) => {
                this.checkBtn = c;
              }}
            />
          </Form>
        </div>
      </div>
    );
  }
}
