import '../styles/LoginViewDesktop.css'
import React from "react";
import ApiHelper from "../ApiHelper";

export default class Login extends React.Component{
    constructor(props) {
        super(props)
        this.props = props
        this.state = {
            name: "",
            surname: "",
            age: "",
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
        let name = login ? null : document.getElementById('nameInput').value
        let surname = login ? null : document.getElementById('surnameInput').value
        let age = login ? null : document.getElementById('ageInput').value
        let error = false
        if(username.length === 0){
            alert("Please enter username!")
            error = true
        }
        else if(password.length === 0){
            alert("Please enter password!")
            error = true
        }
        else if(login === false) {
            if (password !== passwordRepeat) {
                alert("Passwords don't match!")
                error = true
            } else if (name.length === 0) {
                alert("Please enter name!")
                error = true
            } else if (surname.length === 0) {
                alert("Please enter surname!")
                error = true
            } else if (age.length === 0) {
                alert("Please enter age!")
                error = true
            } else if (email.length === 0) {
                alert("Please enter email!")
                error = true
            }
        }

        event.preventDefault()
        if(error){
            return false
        }

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
            ApiHelper.Register(name, surname, age, username, password, email).then(response => {
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
        return true
    }

    render(){
        let login = this.props.login
        return (
            <div className="formWrapper">
                <div/>
                <div/>
                <form onSubmit={e => this.login(e, login)}>
                    {login ?
                        (<></>) :
                        (<div>
                            <div className="form-group">
                                <label htmlFor="nameInput">Name</label>
                                <input type="text" className="form-control" id="nameInput"
                                       placeholder="Enter name" value={this.state.name}
                                       onChange={e => this.setState({name: e.target.value})}/>
                            </div>
                            <div className="form-group">
                                <label htmlFor="nameInput">Surname</label>
                                <input type="text" className="form-control" id="surnameInput"
                                       placeholder="Enter surname" value={this.state.surname}
                                       onChange={e => this.setState({surname: e.target.value})}/>
                            </div>
                            <div className="form-group">
                                <label htmlFor="nameInput">Age</label>
                                <input type="text" className="form-control" id="ageInput"
                                       placeholder="Enter age" value={this.state.age}
                                       onChange={e => this.setState({age: e.target.value})}/>
                            </div>
                            <div className="form-group">
                                <label htmlFor="emailInput">Email</label>
                                <input type="text" className="form-control" id="emailInput" aria-describedby="emailHelp"
                                       placeholder="Enter email" value={this.state.email}
                                       onChange={e => this.setState({email: e.target.value})}/>
                            </div>
                        </div>)
                    }
                    <div className="form-group">
                        <label htmlFor="usernameInput">Username</label>
                        <input type="text" className="form-control" id="usernameInput" aria-describedby="usernameHelp"
                               placeholder="Enter username" value={this.state.username}
                               onChange={e => this.setState({username: e.target.value})}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="passwordInput">Password</label>
                        <input type="password" id="passwordInput" className="form-control" placeholder="Password"
                               value={this.state.password} onChange={e => this.setState({password: e.target.value})}/>
                        {login ?
                            (<></>) :
                            (<input type="password" id="passwordRepeatInput" className="form-control"
                                    placeholder="Repeat password"
                                    value={this.state.passwordRepeat}
                                    onChange={e => this.setState({passwordRepeat: e.target.value})}/>)
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