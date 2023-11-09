import './styles/App.css';
import Recipes from "./pages/Recipes";
import Login from "./pages/Login"
import Header from "./components/Header"
import {BrowserRouter, Routes, Route} from "react-router-dom";

function App() {
    return (<>
        <Header/>
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Recipes/>}/>
                <Route path="/login" element={<Login login={true}/>}/>
                <Route path="/signup" element={<Login login={false}/>}/>
            </Routes>
        </BrowserRouter>
    </>);
}

export default App;
