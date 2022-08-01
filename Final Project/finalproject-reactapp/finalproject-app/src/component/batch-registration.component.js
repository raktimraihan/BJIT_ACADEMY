import Input from "react-validation/build/input";
import Form from "react-validation/build/form";
import Select from "react-validation/build/select";
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

let data = [
  { value: 18, label: "eighteen" },
  { value: 20, label: "twenty" },
  { value: 10, label: "Ten" },
];

const batchNameCheck = (value) => {
  if (value.length < 3 || value.length > 20) {
    return (
      <div className="alert alert-danger" role="alert">
        This must be between 3 and 20 characters.
      </div>
    );
  }
};

export default class BatchRegistration extends Component {
  constructor(props) {
    super(props);

    this.handleRegister = this.handleRegister.bind(this);
    this.onChangeBatchName = this.onChangeBatchName.bind(this);
    this.onChangeActive = this.onChangeActive.bind(this);

    this.state = {
      batchName: "",
      active: true,
      successful: false,
      message: "",
    };
  }

  onChangeBatchName(e) {
    this.setState({
      batchName: e.target.value,
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
      userService.registerBatch(this.state.batchName, this.state.active).then(
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
                  <label htmlFor="batchName">Batch Name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="batchName"
                    value={this.state.batchName}
                    onChange={this.onChangeBatchName}
                    validations={[required, batchNameCheck]}
                  />
                </div>

                <div class="form-group col-md-4 mt-4 px-4">
                  <label for="inputState">Choose Status</label>
                  <Select
                    name="status"
                    value={this.state.active}
                    onChange={this.onChangeActive}
                    validations={[required]}
                    className="form-control"
                  >
                    <option value="">Choose Batch Status</option>
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
