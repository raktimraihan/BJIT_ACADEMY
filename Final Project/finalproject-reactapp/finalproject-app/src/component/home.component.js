import React, { Component } from "react";

export default class Home extends Component{

    constructor(props){
        super(props);

        this.state = {
            home_url: "http://localhost:3000/"
        }
        
    }

    render(){
        return(
            <>
             <p>Hello</p>
            </>
        )
    }
}