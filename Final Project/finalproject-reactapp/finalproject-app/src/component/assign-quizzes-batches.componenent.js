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

export default class QuizBatchAssign extends Component {
  constructor(props) {
    super(props);

    this.handleRegister = this.handleRegister.bind(this);
    this.onChangeBatchName = this.onChangeBatchName.bind(this);
    this.onChangeQuizName = this.onChangeQuizName.bind(this);
    // this.getCustomQuizList = this.getCustomQuizList.bind(this);
    

    this.state = {
      batchName: "",
      quizName: "",
      quizList: [],
      successful: false,
      message: "",
    };
  }

  onChangeBatchName(e) {
    this.setState({
      batchName: e.target.value,
    });
    this.getCustomQuizList(e.target.value);
  }

  onChangeQuizName(e) {
    this.setState({
      quizName: e.target.value,
    });
    console.log(e.target.value);
  }

  getCustomQuizList(value) {
    userService?.getQuizNameAsPerBatch(value).then((response) => {
      this.setState({
        quizList: response.data,
      });
    });

    console.log(this.batchName+" "+ this.quizName);
  }

  handleRegister(e) {
    e.preventDefault();

    this.setState({
      message: "",
      successful: false,
    });

    this.form.validateAll();
    
    if (this.checkBtn.context._errors.length === 0) {
        
      userService.insertQuizBatchConnecting(this.state.batchName, this.state.quizName).then(
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
                  <label htmlFor="Quiz Name">Quiz Name</label>
                  <Select
                    name="role"
                    value={this.state.quizName}
                    onChange={this.onChangeQuizName}
                    validations={[required]}
                    className="form-control"
                  >
                    <option value="">Choose Assigned Quiz</option>

                    {this.state.quizList.map((valueAc) => (
                      <option value={valueAc}>{valueAc}</option>
                    ))}
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
                //window.location.reload();
              }, 5000),
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
