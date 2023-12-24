import SavedRecipes from "../components/SavedRecipes";
import React from 'react';
import '../styles/DisplayRecipes.css';

function SavedRecipesPage() {
    return (
        <div>
            <h1 className="title">Spremljeni recepti</h1>
            <div className="container">
                <SavedRecipes />
            </div>
        </div>
    );
}


export default SavedRecipesPage;
