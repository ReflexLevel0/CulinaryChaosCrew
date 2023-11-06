import '../styles/LoginViewDesktop.css'
import {useState} from "react";

function Login(){
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    return(
        <div className="formWrapper">
            <div/>
            <div/>
            <form>
                <div className="form-group">
                    <label htmlFor="usernameInput">Username</label>
                    <input type="text" className="form-control" id="usernameInput" aria-describedby="usernameHelp" placeholder="Enter username"
                           minLength={6} required={true} value={username} onChange={e => setUsername(e.target.value)}/>
                </div>
                <div className="form-group">
                    <label htmlFor="passwordInput">Password</label>
                    <input type="password" className="form-control" id="passwordInput" placeholder="Password"
                           minLength={6} required={true} value={password} onChange={e => setPassword(e.target.value)}/>
                </div>
                <div className="buttonWrapper">
                    <button type="login" className="btn btn-primary" onClick={() => alert(`log in with ${username} ${password}`)}>Login</button>
                    <button type="register" className="btn btn-primary" onClick={() => alert(`sign up with ${username} ${password}`)}>Sign up</button>
                </div>
            </form>
            <div/>
            <div/>
        </div>
    )
}

export default Login;