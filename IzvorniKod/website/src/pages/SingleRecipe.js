import React from 'react';
import RecipeView from '../components/RecipeView';
import '../styles/SingleRecipeView.css';

const sampleRecipe = {
    name: 'Delicious Recipe',
    category: 'Dessert',
    ingr: ['Ingredient 1', 'Ingredient 2'],
    instr: 'Step 1. Do this. Step 2. Do that.',
    origin: 'Italy',
    tags: ['Tag1', 'Tag2'],
    url: 'https://c2.staticflickr.com/4/3374/3572925000_693b458fcb_b.jpg',
    likes: 5,
};

function SingleRecipe() {
    return (
        <div className="recipe-details-page">
            <h1>Recipe information</h1>
            <RecipeView {...sampleRecipe} />
        </div>
    );
}

export default SingleRecipe;
