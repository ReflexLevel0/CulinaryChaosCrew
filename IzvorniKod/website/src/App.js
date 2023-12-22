import './styles/App.css';
import Recipes from "./pages/Recipes";
import Login from "./pages/Login"
import Header from "./components/Header"
import {Routes, Route, useNavigate} from "react-router-dom";
import {useState} from "react";
import SingleRecipe from './pages/SingleRecipe';

function App() {
    const [username, setUsername] = useState("");
    const [loggedIn, setLoggedin] = useState(false);
    const navigate = useNavigate();

    const handleLogin = (uname) => {
        setUsername(uname);
        setLoggedin(true);
        navigate('/');
    };

    const handleSignout = () => {
        setUsername("")
        setLoggedin(false)
        navigate('/')
    }

    return (
        <>
            <Header loggedIn={loggedIn} username={username} signedOut={handleSignout} />
            <Routes>
                <Route path="/" element={<Recipes />} />
                <Route path="/login" element={<Login login={true} loggedIn={handleLogin} />} />
                <Route path="/signup" element={<Login login={false} />} />
                <Route path="/recipes" element={<Recipes />} />
                <Route path="/singleRecipe" element={<SingleRecipe />} />
            </Routes>
        </>
    );
}

export default App;
