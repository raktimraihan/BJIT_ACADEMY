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

const requiredHour = (value) => {
  if (value < BatchCourseAssign.startHour) {
    return (
      <div className="alert alert-danger" role="alert">
        End Time Can't be Lower.
      </div>
    );
  }
};

export default class BatchCourseAssign extends Component {
  constructor(props) {
    super(props);

    this.handleRegister = this.handleRegister.bind(this);
    this.onChangeBatchName = this.onChangeBatchName.bind(this);
    this.onChangeCourseName = this.onChangeCourseName.bind(this);
    this.onChangeStartHour = this.onChangeStartHour.bind(this);
    this.onChangeEndHour = this.onChangeEndHour.bind(this);

    this.state = {
      batchName: "",
      courseName: "",
      startHour: "",
      endHour: "",
      courseList: [],
      successful: false,
      message: "",
      checkHour: true,
    };
  }

  onChangeBatchName(e) {
    this.setState({
      batchName: e.target.value,
    });
    this.getCustomCourseList(e.target.value);
  }

  onChangeCourseName(e) {
    this.setState({
      courseName: e.target.value,
    });
  }

  onChaneCheckHour(e) {
    this.setState({
      checkHour: e.target.value,
    });
  }

  onChangeStartHour(e) {
    this.setState({
      startHour: e.target.value,
    });
  }

  onChangeEndHour(e) {
    this.setState({
      endHour: e.target.value,
    });
  }

  getCustomCourseList(value) {
    userService?.getCourseNameAsPerBatch(value).then((response) => {
      this.setState({
        courseList: response.data,
      });
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

      userService
        .insertCourseBatchConnecting(
          this.state.courseName,
          this.state.batchName,
          this.state.startHour,
          this.state.endHour
        )
        .then(
          (response) => {
            this.setState({
              message: response.data.message,
              successful: true,
              //checkHour: true
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
              //checkHour: true
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
                  <label htmlFor="courseName">Batch</label>
                  <Select
                    name="batchName"
                    value={this.state.batchName}
                    onChange={this.onChangeBatchName}
                    validations={[required]}
                    className="form-control"
                  >
                    <option value="">Choose Designated Batch</option>
                    <OptionMapper
                      axiosMethod={userService.getBatchesNamesOnly()}
                    />
                  </Select>
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Course</label>
                  <Select
                    name="role"
                    value={this.state.courseName}
                    onChange={this.onChangeCourseName}
                    validations={[required]}
                    className="form-control"
                  >
                    <option value="">Choose Assigned Course</option>

                    {this.state.courseList.map((valueAc) => (
                      <option value={valueAc}>{valueAc}</option>
                    ))}
                  </Select>
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">Start Hour</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="startHour"
                    value={this.state.startHour}
                    onChange={this.onChangeStartHour}
                    validations={[required]}
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="courseName">End Hour</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="endHour"
                    value={this.state.endHour}
                    onChange={this.onChangeEndHour}
                    validations={[required, requiredHour]}
                  />
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
               // window.location.reload();
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
