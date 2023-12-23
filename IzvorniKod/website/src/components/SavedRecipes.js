import React from 'react';
import RecipeCard from './RecipeCard';
import '../styles/SavedRecipes.css';

function SavedRecipes() {
    //trebalo bi implementirati tako da se preko recipelist dohvate rezultati iz baze, pa nakon toga ovo sto slijedi
    //za sada sam samo isprobavao ovu funkcionalnost sa savedRecipes varijablom
    const savedRecipes = [
        {
            name: 'Delicious Pasta',
            instructions: 'Cook pasta with sauce...',
            url: 'https://c2.staticflickr.com/4/3374/3572925000_693b458fcb_b.jpg',
        },
        {
            name: 'Delicious Pasta',
            instructions: 'Cook pasta with sauce...',
            url: 'https://c2.staticflickr.com/4/3374/3572925000_693b458fcb_b.jpg',
        },
        {
            name: 'Delicious Pasta',
            instructions: 'Cook pasta with sauce...',
            url: 'https://c2.staticflickr.com/4/3374/3572925000_693b458fcb_b.jpg',
        },
        {
            name: 'Delicious Pasta',
            instructions: 'Cook pasta with sauce...',
            url: 'https://c2.staticflickr.com/4/3374/3572925000_693b458fcb_b.jpg',
        }
    ];
    return (
        <div className="saved-recipes">
            <h1>Spremljeni recepti</h1>
            <div className="saved-recipes-list">
            {savedRecipes.map(r => <RecipeCard recipeName={r.name} description={r.instructions} imageSrc={r.url}/>)}
            </div>
        </div>
    );
}
export default SavedRecipes;
