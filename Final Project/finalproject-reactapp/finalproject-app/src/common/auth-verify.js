import React,{Component} from "react";
import {withRouter} from "react-router-dom";

const parseJWT = (token) => {
    try{
        return JSON.parse(atob(token.split('.')[1]));
    }catch(e){
        return null;
    }
};

class AuthVerify extends Component{
    constructor(props){
        super(props);

        props.history.listen(() => {

            const user = JSON.parse(localStorage.getItem("user"));

            if(user){
                const decodedJwt = parseJWT(user.accessToken);

                if(decodedJwt.exp * 1000 < Date.now()){
                    props.logOut();
                }
            }
        });
    }

    render(){
        return <div></div>
    }
}

export default withRouter(AuthVerify);