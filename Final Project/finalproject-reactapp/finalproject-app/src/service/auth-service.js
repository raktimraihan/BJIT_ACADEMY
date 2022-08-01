import axios from "axios";

const API_URL = "http://localhost:8080/";

class AuthService{
    login(username, password){
        return axios.post(API_URL + "login",{
                username,
                password
            }).then(
                response => {
                    if(response.data.accessToken){
                        localStorage.setItem("user", JSON.stringify(response.data));
                        localStorage.setItem("userName", JSON.stringify(response.data.userName))
                        console.log(response.data.userName);
                    }

                    return response.data;
                }
            )
        
    }

    logout(){
        localStorage.removeItem("user");
        localStorage.removeItem("userName");
    }

    getCurrentUser(){
        return JSON.parse(localStorage.getItem("user"));
    }

    getCurrentUserName(){
        return JSON.parse(localStorage.getItem("userName"));
    }
}

export default new AuthService();