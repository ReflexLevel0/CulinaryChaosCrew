import '../styles/LoginViewDesktop.css'
import React from "react";
import ApiHelper from "../ApiHelper";

export default class Login extends React.Component{
    constructor(props) {
        super(props)
        this.props = props
        this.state = {
            username: "",
            password: "",
            passwordRepeat: "",
            email: ""
        }
    }

    login(event, login){
        let username = document.getElementById("usernameInput").value
        let password = document.getElementById("passwordInput").value
        let passwordRepeat = login ? null : document.getElementById("passwordRepeatInput").value
        let email = login ? null : document.getElementById('emailInput').value
        if(username.length === 0){
            alert("Please enter username!")
        }
        else if(login === false && username.length < 6){
            alert("Username should be at least 6 characters long!")
        }
        else if(password.length === 0){
            alert("Please enter password!")
        }
        else if(login === false && password.length < 6){
            alert("Password should be at least 6 characters long!")
        }
        else if(login === false && password !== passwordRepeat) {
            alert("Passwords don't match!")
        }else if(login === false && email.length === 0){
            alert("Please enter email!")
        }
        else{
            //Trying to log in with provided data
            if(login){
                ApiHelper.Login(username, password).then(response => {
                    //If log in is successful
                    if(response.status === 200){
                        this.props.loggedIn(username)
                    }

                    //If log in failed
                    else{
                        response.json().then(json => alert(json.message))
                    }
                })
            }else{
                console.log(email)
                ApiHelper.Register(username, password, email).then(response => {
                    //If register is successful
                    if(response.status === 200){
                        window.location.href = '/login'
                    }

                    //If register failed
                    else{
                        response.json().then(json => alert(json.message))
                    }
                })
            }
            event.preventDefault()
            return true
        }
        event.preventDefault()
        return false
    }

    render(){
        let login = this.props.login
        return(
            <div className="formWrapper">
                <div/>
                <div/>
                <form onSubmit={e => this.login(e, login)}>
                    {login ?
                        (<></>) :
                        (<div className="form-group">
                            <label htmlFor="emailInput">Email</label>
                            <input type="text" className="form-control" id="emailInput" aria-describedby="emailHelp" placeholder="Enter email" value={this.state.email} onChange={e => this.setState({email: e.target.value})}/>
                        </div>)
                    }
                    <div className="form-group">
                        <label htmlFor="usernameInput">Username</label>
                        <input type="text" className="form-control" id="usernameInput" aria-describedby="usernameHelp" placeholder="Enter username" value={this.state.username} onChange={e => this.setState({username: e.target.value})}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="passwordInput">Password</label>
                        <input type="password" id="passwordInput" className="form-control" placeholder="Password" value={this.state.password} onChange={e => this.setState({password: e.target.value})}/>
                        {login ?
                            (<></>) :
                            (<input type="password" id="passwordRepeatInput" className="form-control" placeholder="Repeat password"
                                    value={this.state.passwordRepeat} onChange={e => this.setState({passwordRepeat: e.target.value})} />)
                        }
                    </div>
                    {login ?
                        (<button type="submit" className="btn btn-primary">Login</button>) :
                        (<button type="submit" className="btn btn-primary">Register</button>)
                    }
                </form>
                <div/>
                <div/>
            </div>
        )
    }
}