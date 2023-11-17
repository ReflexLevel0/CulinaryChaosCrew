import RecipeCard from "./RecipeCard";
import apiHelper from "../ApiHelper";
import {useEffect, useState} from "react";

// let dummyRecipes = [
    // {
    //     name: "Grah",
    //     description: "Varivo od graha",
    //     imageSrc: "https://live.staticflickr.com/6209/6063498585_33b78332cc.jpg",
    // },
    // {
    //     name: "Palenta",
    //     description: "Ovo je recept za palentu",
    //     imageSrc: "https://1.bp.blogspot.com/_AWEhM2Nug4g/SV2M1PypdDI/AAAAAAAAC3s/fIKgKOVVgo4/s400/Polenta+4.jpg",
    // },
    // {
    //     name: "Pizza",
    //     description: "Recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu",
    //     imageSrc: "https://c2.staticflickr.com/4/3374/3572925000_693b458fcb_b.jpg",
    // },
// ]

function RecipeList(){
    const [recipes, setRecipes] = useState([])

    //Loading recipes
    useEffect(() => {
        apiHelper.GetRecipes().then(recipeList => {
            setRecipes(recipeList)
        })
    }, []);

    //Rendering recipe list after recipes have been loaded in
    return (<>
        {recipes.map(r => <RecipeCard key={r.name} recipeName={r.name} description={r.instructions} imageSrc={r.url}/>)}
    </>);
}

export default RecipeList;