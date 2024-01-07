import '../styles/Header.css'

function Header({username, loggedIn, signedOut}) {
    console.log(username)
    console.log(loggedIn)
    return (<>
        <div className="headerWrapper">
            <button className="btn" onClick={() => window.location.href = '/'}>
                <img src="../../images/CookBooked_logo.png" alt="Logo" id="logo_img"/>
            </button>
            <div className="rightButtonWrapper">
                {loggedIn ?
                    (<>
                        <div className="usernameText">{username}</div>
                        <button className="btn" onClick={signedOut}>SIGN OUT</button>
                    </>) :
                    (<>
                        <button className="btn" onClick={() => window.location.href = '/login'}>LOGIN</button>
                        <button className="btn" onClick={() => window.location.href = '/signup'}>SIGN UP</button>
                    </>)
                }
            </div>
        </div>
    </>)
}

export default Header;