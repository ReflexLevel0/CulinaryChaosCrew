import './styles/App.css';
import Recipes from "./pages/Recipes";
import Login from "./pages/Login";
import Header from "./components/Header";
import { Routes, Route, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import SingleRecipe from './pages/SingleRecipe';
import SavedRecipesPage from './pages/SavedRecipesPage';
import Home from './pages/Home';
import ErrorPage from './pages/Errorpage';
import ProfilePage from './pages/ProfilePage';
import CreateRecipePage from './pages/CreateRecipePage';

function App() {
    const [username, setUsername] = useState("");
    const [loggedIn, setLoggedIn] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        // Check if the user is already logged in (info exists in localStorage)
        const storedUsername = localStorage.getItem('username');
        if (storedUsername) {
            setUsername(storedUsername);
            setLoggedIn(true);
        }
    }, []); // The empty dependency array ensures this effect runs only once on mount

    const handleLogin = (uname) => {
        localStorage.setItem('username', uname);
        setUsername(uname);
        setLoggedIn(true);
        navigate('/');
    };

    const handleSignout = () => {
        localStorage.removeItem('username');
        setUsername('');
        setLoggedIn(false);
        navigate('/');
    }

    return (
        <>
            <Header loggedIn={loggedIn} username={username} signedOut={handleSignout} />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login login={true} loggedIn={handleLogin} />} />
                <Route path="/signup" element={<Login login={false} />} />
                <Route path="/recipes" element={<Recipes />} />
                <Route path="/singleRecipe" element={<SingleRecipe />} />
                <Route path="/saved" element={<SavedRecipesPage />} />
                <Route path="/recipe/:id" element={<SingleRecipe />} />
                <Route path="/profile" element={<ProfilePage />} />
                <Route path="/create" element={<CreateRecipePage />} />
                <Route path="*" element={<ErrorPage />} />
            </Routes>
        </>
    );
}

export default App;
