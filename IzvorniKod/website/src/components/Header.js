import '../styles/Header.css'
import Search from './Search';

function Header({username, loggedIn, signedOut}) {


    console.log(username)
    console.log('logged in:' + loggedIn)
    return (<>
        <div className="headerWrapper">
            <button className="btn" onClick={() => window.location.href = '/'}>
                <img src="../../images/CookBooked_logo.png" alt="Logo" id="logo_img"/>
            </button>
            <div className="centerContent">
                <Search />
            </div>
            <div className="rightButtonWrapper">
                {loggedIn ?
                    (<>
                        <a href="/profile" className="profileLinkButton">
                            <img src="https://www.pngkey.com/png/full/202-2024691_my-profile-comments-my-profile-icon-png.png" alt="Profile" className="profileIcon" />
                            <div>
                                <span className="usernameText">{username}</span>
                            </div>
                        </a>
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