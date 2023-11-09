import '../styles/Header.css'

function Header() {
    return (<>
        <div className="headerWrapper">
            <button className="btn" onClick={() => window.location.href = '/'}>LOGO IMAGE</button>
            <div className="rightButtonWrapper">
                <button className="btn" onClick={() => window.location.href = '/login'}>LOGIN</button>
                <button className="btn" onClick={() => window.location.href = '/signup'}>SIGN UP</button>
            </div>
        </div>
    </>)
}

export default Header;