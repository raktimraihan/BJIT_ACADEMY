import { Component, React, useEffect, useState } from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";

import "bootstrap/dist/css/bootstrap.min.css";
import "../node_modules/bootstrap/dist/js/bootstrap.bundle.min.js";
import "./App.css";

import AuthService from "./service/auth-service";
import EventBus from "./common/event-bus";
import Login from "./component/login.component";
import Profile from "./component/profile.component.js";
import BatchRegister from "./component/batch-registration.component.js";
import CourseRegistration from "./component/course-register.component.js";
import TraineeRegistration from "./component/trainee-register.component.js";
import BatchActvInactv from "./component/batch-course-asgn.component.js";
import BatchCourseAssign from "./component/batch-course-asgn.component.js";
import QuizRegistration from "./component/quiz-register.componenet.js";
import QuizBatchAssign from "./component/assign-quizzes-batches.componenent.js";
import TrainerRegistration from "./component/trainer-register.component.js";
import TrainerManagement from "./component/trainer-view-all.component.js";
import TableBodyTrainerDataAll from "./component/subcomponent/table-response-trainer.component.js";
import TrainerEdit from "./component/trainer-edit.component.js";
import TrainerDelete from "./component/trainer-delete.component.js";
import TrainerCourseAssign from "./component/trainer-course-assign.component.js";
import TrainerCourseDelete from "./component/trainee-course-delete.component.js";
import TraineeManagement from "./component/trainee-management.component.js";
import TraineeEdit from "./component/trainee-edit.component.js";
import TraineeDelete from "./component/trainee-delete.component.js";
import TraineeBatchAssign from "./component/trainee-batch-assign.component.js";
import QuizViewNotReviewed from "./component/quiz-view-not-reviewed.component.js";
import QuizGradeSubmission from "./component/quiz-grade-submission.component.js";
import QuizViewReviewed from "./component/quiz-reviewed.component.js";
import QuizViewReviewedModerator from "./component/quiz-viewed-moderator.component.js";
import ModeratorClassRoutin from "./component/moderator-class-routin.component.js";
import TraineeClassRoutin from "./component/trainee-routin.component.js";
import QuizSubmissionUser from "./common/quiz-submission-user.component.js";
import QuizSubmissionUserForm from "./component/quiz-grade-submission-user.component.js";
import QuizViewResult from "./component/user-result.component.js";
import TrainerClassRoutin from "./component/trainer-schedule.component.js";
import QuizViewRecentSubmission from "./component/quiz-view-recentsubmission.component.js";
import BatchManagementViewAll from "./component/batch-manage.component.js";
import CourseFromBatchDelete from "./component/subcomponent/remove-course-from-batch.component.js";

class App extends Component {
  constructor(props) {
    super(props);
    this.logOut = this.logOut.bind(this);

    this.state = {
      currentUser: undefined,
      showAdminBoard: false,
      showModeratorBoard: false,
      showUserBoard: false
    };
  }

  componentDidMount() {
    const user = AuthService.getCurrentUser();

    if (user) {
      this.setState({
        currentUser: user,
        showModeratorBoard: user.roles.includes("ROLE_MODERATOR"),
        showAdminBoard: user.roles.includes("ROLE_ADMIN"),
        showUserBoard: user.roles.includes("ROLE_USER")
      });
    }

    EventBus.on("logout", () => {
      this.logOut();
    });
  }

  componentWillUnmount() {
    EventBus.remove("logout");
  }

  logOut() {
    AuthService.logout();
    this.setState({
      currentUser: undefined,
      showAdminBoard: false,
      showModeratorBoard: false,
      showUserBoard: false
    });
  }

  render() {
    const { currentUser, showModeratorBoard, showAdminBoard, showUserBoard } = this.state;

    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/"} className="navbar-brand">
            Training Academy
          </Link>
          <div className="navbar-nav mr-auto">

            {showUserBoard && (
              <li className="nav-item">
                <Link to={"/trainee-routin"} className="nav-link">
                  Class Routin
                </Link>
              </li>
              
            )}

            {showUserBoard && (
              <li className="nav-item">
                <Link to={"/quiz-submission"} className="nav-link">
                  Quiz Submission
                </Link>
              </li>
              
            )}

            {showUserBoard && (
              <li className="nav-item">
                <Link to={"/result"} className="nav-link">
                  Result
                </Link>
              </li>
              
            )}

            {showModeratorBoard && (
              <li className="nav-item">
                <Link to={"/trainer-routin"} className="nav-link">
                  Class Routin
                </Link>
              </li>
              
            )}

            
            {showModeratorBoard && (
              <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                  <li class="nav-item dropdown">
                    <a
                      class="nav-link dropdown-toggle"
                      href="#"
                      id="navbarDropdownMenuLink"
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false"
                    >
                      Quiz
                    </a>
                    <div
                      class="dropdown-menu"
                      aria-labelledby="navbarDropdownMenuLink"
                    >
                      <Link class="dropdown-item" to="/quiz-view-submission">
                        Recent Submissions{" "}
                      </Link>
                      <Link class="dropdown-item" to="/quiz-view">
                        Recently Assigned{" "}
                      </Link>
                      <Link class="dropdown-item" to="/quiz-view-reviewed-mod">
                        Reviewed Submissions{" "}
                      </Link>
                      
                    </div>
                  </li>
                </ul>
              </div>
            )}  



            {showAdminBoard && (
              <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                  <li class="nav-item dropdown">
                    <a
                      class="nav-link dropdown-toggle"
                      href="#"
                      id="navbarDropdownMenuLink"
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false"
                    >
                      Profile
                    </a>
                    <div
                      class="dropdown-menu"
                      aria-labelledby="navbarDropdownMenuLink"
                    >
                      <Link class="dropdown-item" to="/trainee-register">
                        Add Trainee{" "}
                      </Link>
                      <Link class="dropdown-item" to="/trainer-registration">
                        Add Trainer{" "}
                      </Link>
                      <Link class="dropdown-item" to="/manage-trainee">
                        Manage Trainee{" "}
                      </Link>
                      <Link class="dropdown-item" to="/manage-trainers">
                        Manage Trainer{" "}
                      </Link>
                    </div>
                  </li>
                </ul>
              </div>
            )}

            {showAdminBoard && (
              <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                  <li class="nav-item dropdown">
                    <a
                      class="nav-link dropdown-toggle"
                      href="#"
                      id="navbarDropdownMenuLink"
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false"
                    >
                      Course
                    </a>
                    <div
                      class="dropdown-menu"
                      aria-labelledby="navbarDropdownMenuLink"
                    >
                      <Link class="dropdown-item" to="/course-register">
                        Add Course{" "}
                      </Link>
                      <Link class="dropdown-item" to="#">
                        {/* Manage Course{" "} */}
                      </Link>
                    </div>
                  </li>
                </ul>
              </div>
            )}

            {showAdminBoard && (
              <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                  <li class="nav-item dropdown">
                    <a
                      class="nav-link dropdown-toggle"
                      href="#"
                      id="navbarDropdownMenuLink"
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false"
                    >
                      Batch
                    </a>
                    <div
                      class="dropdown-menu"
                      aria-labelledby="navbarDropdownMenuLink"
                    >
                      <Link class="dropdown-item" to="/bregister">
                        Add Batch{" "}
                      </Link>
                      <Link class="dropdown-item" to="/actv-inac-batch">
                        Assign Courses
                      </Link>
                      <Link class="dropdown-item" to="/manage-batch">
                        Manage Batch{" "}
                      </Link>
                    </div>
                  </li>
                </ul>
              </div>
            )}

            {showAdminBoard && (
              <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                  <li class="nav-item dropdown">
                    <a
                      class="nav-link dropdown-toggle"
                      href="#"
                      id="navbarDropdownMenuLink"
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false"
                    >
                      Quiz
                    </a>
                    <div
                      class="dropdown-menu"
                      aria-labelledby="navbarDropdownMenuLink"
                    >
                      <Link class="dropdown-item" to="/quiz-register">
                        Add Quiz{" "}
                      </Link>
                      <Link class="dropdown-item" to="/assign-quizzes-batch">
                        Manage Quiz
                      </Link>
                      <Link class="dropdown-item" to="/quiz-view-submission">
                        Recent Submissions{" "}
                      </Link>
                      <Link class="dropdown-item" to="/quiz-view">
                        Recently Assigned{" "}
                      </Link>
                      <Link class="dropdown-item" to="/quiz-view-reviewed">
                        Reviewed Quizzes{" "}
                      </Link>
                    </div>
                  </li>
                </ul>
              </div>
            )}

            {currentUser && (
              <li className="nav-item">
                <Link to={"/user"} className="nav-link">
                  {/* User  */}
                </Link>
              </li>
            )}
          </div>

          {currentUser ? (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to={"/profile"} className="nav-link">
                  {currentUser.userName}
                </Link>
              </li>
              <li className="nav-item">
                <a href="/login" className="nav-link" onClick={this.logOut}>
                  Log out
                </a>
              </li>
            </div>
          ) : (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to={"/login"} className="nav-link">
                  Login
                </Link>
              </li>

              <li className="nav-item">
                <Link to={"/register"} className="nav-link">
                  {/* Sign Up */}
                </Link>
              </li>
            </div>
          )}
        </nav>

        <div className="container col-md-6 col-md-offset-4 mt-5">
          <Routes>
            <Route eaxct path="/edit" element={<TrainerEdit />}></Route>
            <Route eaxct path="/delete" element={<TrainerDelete />}></Route>

            <Route eaxct path="/new-course-assign-trainer" element={<TrainerCourseAssign />}></Route>
            <Route eaxct path="/remove-course-assign-trainer" element={<TrainerCourseDelete />}></Route>

            <Route exact path="/login" element={<Login />} />
            <Route exact path="/profile" element={<Profile />} />
            <Route exact path="/bregister" element={<BatchRegister />} />

            <Route exact path="/quiz-register" element={<QuizRegistration />} />
            <Route exact path="/quiz-grade-submission" element={<QuizGradeSubmission />} />

            <Route exact path="/edit-trainee" element={<TraineeEdit />} />
            <Route exact path="/delete-trainee" element={<TraineeDelete />} />
            <Route exact path="/trainee-assign-new-batch" element={<TraineeBatchAssign />} />

            <Route exact path="/quiz-grade-submission-user" element={<QuizSubmissionUserForm />} />

            <Route exact path="/remove-course-from-batch" element={<CourseFromBatchDelete />} />
            
            
            <Route
              exact
              path="/assign-quizzes-batch"
              element={<QuizBatchAssign />}
            />
            <Route
              exact
              path="/trainer-registration"
              element={<TrainerRegistration />}
            />
            
            <Route
              exact
              path="/course-register"
              element={<CourseRegistration />}
            />
            <Route
              exact
              path="/trainee-register"
              element={<TraineeRegistration />}
            />
            <Route
              exact
              path="/actv-inac-batch"
              element={<BatchCourseAssign />}
            />

            {/* <Route eaxct path="/edit" element={<TrainerEdit />}></Route> */}
          </Routes>
          
        </div>

        <div className="container col-md-10 col-md-offset-5 mt-0">
          <Routes>
            <Route path="/manage-trainers" element={<TrainerManagement />}></Route>
            <Route path="/manage-trainee" element={<TraineeManagement/>}></Route>

            <Route path="/quiz-view" element={<QuizViewNotReviewed/>}></Route>
            <Route path="/quiz-view-submission" element={<QuizViewRecentSubmission/>}></Route>
            <Route path="/quiz-view-reviewed" element={<QuizViewReviewed/>}></Route>
            <Route path="/quiz-view-reviewed-mod" element={<QuizViewReviewedModerator />}></Route>

            <Route path="/mod" element={<ModeratorClassRoutin />}></Route>
            <Route path="/trainee-routin" element={<TraineeClassRoutin />} />
            <Route path="/quiz-submission" element={<QuizSubmissionUser />} />
            <Route path="/result" element={<QuizViewResult />} />

            <Route path="/trainer-routin" element={<TrainerClassRoutin />} />
            <Route path="/manage-batch" element={<BatchManagementViewAll />} />
            
          </Routes>

        </div>
      </div>
    );
  }
}

export default App;
