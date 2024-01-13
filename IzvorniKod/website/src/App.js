import './styles/App.css';
import Recipes from "./pages/Recipes";
import Login from "./pages/Login"
import Header from "./components/Header"
import {Routes, Route, useNavigate} from "react-router-dom";
import {useState} from "react";
import SingleRecipe from './pages/SingleRecipe';
import SavedRecipesPage from './pages/SavedRecipesPage';
import Home from './pages/Home';
import ErrorPage from './pages/Errorpage';
import ProfilePage from './pages/ProfilePage';
import CreateRecipePage from './pages/CreateRecipePage';

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
                <Route path="/" element={<Home/>} />   
                <Route path="/login" element={<Login login={true} loggedIn={handleLogin} />} />
                <Route path="/signup" element={<Login login={false} />} />
                <Route path="/recipes" element={<Recipes />} />
                <Route path="/singleRecipe" element={<SingleRecipe />} />
                <Route path="/saved" element={<SavedRecipesPage/>} />
                <Route path="/recipe/:id" element={<SingleRecipe />} />
                <Route path="/profile" element={<ProfilePage />} />
                <Route path="/create" element={<CreateRecipePage />} />
                <Route path="*" element={<ErrorPage />} />
            </Routes>
        </>
    );
}

export default App;
