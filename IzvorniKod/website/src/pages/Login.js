import '../styles/LoginViewDesktop.css'
import {useState} from "react";

function Login(){
function Login({ login }){
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [passwordRepeat, setPasswordRepeat] = useState("")
    return(
        <div className="formWrapper">
            <div/>
            <div/>
            <form>
                <div className="form-group">
                    <label htmlFor="usernameInput">Username</label>
                    <input type="text" className="form-control" id="usernameInput" aria-describedby="usernameHelp" placeholder="Enter username" value={username} onChange={e => setUsername(e.target.value)}/>
                </div>
                <div className="form-group">
                    <label htmlFor="passwordInput">Password</label>
                    <input type="password" id="passwordInput" className="form-control" placeholder="Password" value={password} onChange={e => setPassword(e.target.value)}/>
                    {login ?
                        (<></>) :
                        (<input type="password" id="passwordRepeatInput" className="form-control" placeholder="Repeat password"
                           value={passwordRepeat} onChange={e => setPasswordRepeat(e.target.value)} />)
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

export default Login;