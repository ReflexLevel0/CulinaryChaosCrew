import '../styles/Header.css'

function Header() {
    return (<>
        <div className="headerWrapper">
            <button className="btn" onClick={() => window.location.href = '/'}>LOGO IMAGE</button>
            <button className="btn" onClick={() => window.location.href = '/login'}>LOGIN</button>
        </div>
    </>)
}

export default Header;