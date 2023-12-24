import React from 'react';
import RecipeList from "../components/RecipeList";
import '../styles/DisplayRecipes.css';

function Recipes() {
    return (
        <div>
            <h1 className="title">Kolekcija recepata</h1>
            <div className="container">
                <RecipeList />
            </div>
        </div>
    );
}

export default Recipes;
