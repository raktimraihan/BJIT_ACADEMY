import Input from "react-validation/build/input";
import Form from "react-validation/build/form";
import TextArea from "react-validation/build/textarea";
import CheckButton from "react-validation/build/button";
import React, { Component } from "react";
import userService from "../service/user-service";

const required = (value) => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};


const courseNameCheck = (value) => {
  if (value.length < 3 || value.length > 20) {
    return (
      <div className="alert alert-danger" role="alert">
        This must be between 3 and 20 characters.
      </div>
    );
  }
};

const courseDesCheck = (value) => {
    if (value.length < 50 || value.length > 1000) {
      return (
        <div className="alert alert-danger" role="alert">
          The username must be between 50 and 1000 characters.
        </div>
      );
    }
  };

export default class CourseRegistration extends Component {
  constructor(props) {
    super(props);

    this.handleRegister = this.handleRegister.bind(this);
    this.onChangeCourseName = this.onChangeCourseName.bind(this);
    this.onChangeDes = this.onChangeDes.bind(this);
    this.onChangeTopic = this.onChangeTopic.bind(this);
    
    this.state = {
      courseName: "",
      topic: "",
      description: "",
      successful: false,
      message: "",
    };
  }

  onChangeCourseName(e){
    this.setState({
        courseName: e.target.value,
    })
  }

  onChangeTopic(e){
    this.setState({
        topic: e.target.value,
    })
  }

  onChangeDes(e){
    this.setState({
        description: e.target.value,
    })
  }
  

  handleRegister(e) {
    e.preventDefault();

    this.setState({
      message: "",
      successful: false,
    });

    this.form.validateAll();

    if (this.checkBtn.context._errors.length === 0) {
        console.log(this.state.courseName+" "+this.state.topic+" "+this.state.description);
      userService.registerCourse(
        this.state.courseName,
        this.state.topic,
        this.state.description
        ).then(
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
                  <label htmlFor="courseName">Course Name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="courseName"
                    value={this.state.courseName}
                    onChange={this.onChangeCourseName}
                    validations={[required, courseNameCheck]}
                  />
                </div>

                <div className="form-group mt-4 px-4">
                  <label htmlFor="topic">Course Topic</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="topic"
                    value={this.state.topic}
                    onChange={this.onChangeTopic}
                    validations={[required]}
                  />
                </div>
                
                <div className="form-group mt-4 px-4">
                  <label htmlFor="topic">Description </label>
                  <TextArea
                    type="textarea"
                    className="form-control"
                    name="description"
                    value={this.state.description}
                    onChange={this.onChangeDes}
                    validations={[required, courseDesCheck]}
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
