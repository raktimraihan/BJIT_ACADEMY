import Input from "react-validation/build/input";
import Form from "react-validation/build/form";
import Select from "react-validation/build/select";
import CheckButton from "react-validation/build/button";
import React, { Component } from "react";
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

const durationCheck = (value) => {
  if (value < 5) {
    return (
      <div className="alert alert-danger" role="alert">
        Duration Can't be less than 5 minutes. 
      </div>
    );
  }
};

const marksCheck = (value) => {
  if (value <= 0) {
    return (
      <div className="alert alert-danger" role="alert">
        Marks can't be 0
      </div>
    );
  }
};

export default class QuizRegistration extends Component {
  constructor(props) {
    super(props);

    this.handleRegister = this.handleRegister.bind(this);
    this.onChangeTitle = this.onChangeTitle.bind(this);
    this.onChangeDuration = this.onChangeDuration.bind(this);
    this.onChangeTotalMarks = this.onChangeTotalMarks.bind(this);
    this.onChangeActive = this.onChangeActive.bind(this);
    this.onChangeNumber = this.onChangeNumber.bind(this);
    this.onChangeCourseName = this.onChangeCourseName.bind(this);
    

    this.state = {
      number: "",
      courseName: "",
      title: "",
      duration: "",
      totalMarks: "",
      active: true,
      successful: false,
      message: "",
    };
  }

  onChangeNumber(e) {
    this.setState({
      number: e.target.value,
    });
  }

  onChangeCourseName(e) {
    this.setState({
      courseName: e.target.value,
    });
  }

  onChangeTitle(e) {
    this.setState({
      title: e.target.value,
    });
  }

  onChangeDuration(e) {
    this.setState({
      duration: e.target.value,
    });
  }

  onChangeTotalMarks(e) {
    this.setState({
      totalMarks: e.target.value,
    });
  }

  onChangeActive(e) {
    this.setState({
      active: e.target.value,
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
      userService.registerQuiz(this.state.totalMarks, "Quiz-0"+this.state.number+": "+this.state.courseName, this.state.duration, this.state.active).then(
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
                  <label htmlFor="batchName">Quiz No:</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="title"
                    value={this.state.number}
                    onChange={this.onChangeNumber}
                    validations={[required]}
                  />
                </div>

                <div class="form-group col-md-6 mt-4 px-4">
                  <label for="inputState">Title: </label>
                  <Select
                    name="status"
                    value={this.state.courseName}
                    onChange={this.onChangeCourseName}
                    validations={[required]}
                    className="form-control"
                  >
                    <option value="">Choose Status</option>
                    <OptionMapper axiosMethod={userService.getCourseNames()} />
                  </Select>
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="batchName">Total Marks</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="totalMarks"
                    value={this.state.totalMarks}
                    onChange={this.onChangeTotalMarks}
                    validations={[required, marksCheck]}
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="batchName">Duration </label>
                  <Input
                    type="text"
                    className="form-control"
                    name="duration"
                    value={this.state.duration}
                    onChange={this.onChangeDuration}
                    validations={[required, durationCheck]}
                  />
                </div>

                <div class="form-group col-md-6 mt-4 px-4">
                  <label for="inputState">Quiz Active/Inactive</label>
                  <Select
                    name="status"
                    value={this.state.active}
                    onChange={this.onChangeActive}
                    validations={[required]}
                    className="form-control"
                  >
                    <option value="">Choose Status</option>
                    <option value="true">Active</option>
                    <option value="false">Expired</option>
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
