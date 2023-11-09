import RecipeCard from "./RecipeCard";
const dummyRecipes = [
    {
        name: "Grah",
        description: "Varivo od graha",
        imageSrc: "https://live.staticflickr.com/6209/6063498585_33b78332cc.jpg",
    },
    {
        name: "Palenta",
        description: "Ovo je recept za palentu",
        imageSrc: "https://1.bp.blogspot.com/_AWEhM2Nug4g/SV2M1PypdDI/AAAAAAAAC3s/fIKgKOVVgo4/s400/Polenta+4.jpg",
    },
    {
        name: "Pizza",
        description: "Recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu recept za pizzu",
        imageSrc: "https://c2.staticflickr.com/4/3374/3572925000_693b458fcb_b.jpg",
    },
]

function RecipeList(){
    return (<>
        {dummyRecipes.map(r => <RecipeCard recipeName={r.name} description={r.description} imageSrc={r.imageSrc}/>)}
    </>);
}

export default RecipeList;