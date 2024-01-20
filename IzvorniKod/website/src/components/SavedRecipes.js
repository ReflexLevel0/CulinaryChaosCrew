import React, { useState, useEffect } from 'react';
import RecipeCard from './RecipeCard';
import ApiHelper from '../ApiHelper';
import '../styles/Saved-RecepieList.css';


function SavedRecipes() {

    const [recipes, setRecipes] = useState([]);

    useEffect(() => {
      const fetchRecipes = async () => {
        try {
          const recipesData = await ApiHelper.GetLikedRecipes(localStorage.getItem("uid"));
          setRecipes(recipesData);
        } catch (error) {
          console.error('Error fetching recipes:', error);
        }
      };
  
      fetchRecipes();
    }, []);

    return (
        <>
            <div className="recipe-cards-container">
                {recipes.map(recipe => (
                    <RecipeCard
                    rid={recipe.rid}
                    recipeName={recipe.name}
                    description={recipe.instructions}
                    imageSrc={recipe.iurl}
                  />
                ))}
            </div>
        </>
    );
}


export default SavedRecipes;
