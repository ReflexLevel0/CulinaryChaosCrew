import './App.css';
import RecipeCard from "./components/RecipeCard";

function App() {
  return (<>
    <RecipeCard recipeName="Grah" description="Varivo od graha" imageSrc="https://live.staticflickr.com/6209/6063498585_33b78332cc.jpg"/>
    <RecipeCard recipeName="Palenta" description="Ovo je recept za palentu" imageSrc="https://1.bp.blogspot.com/_AWEhM2Nug4g/SV2M1PypdDI/AAAAAAAAC3s/fIKgKOVVgo4/s400/Polenta+4.jpg"/>
    <RecipeCard recipeName="Pizza" description="Recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu" imageSrc="https://c2.staticflickr.com/4/3374/3572925000_693b458fcb_b.jpg"/>
  </>);
}

export default App;
