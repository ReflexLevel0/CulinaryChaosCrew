import React, { useState, useEffect } from 'react';
import RecipeCard from './RecipeCard';
import ApiHelper from '../ApiHelper';
import '../styles/Saved-RecepieList.css';


function SavedRecipes() {

    const [recipes, setRecipes] = useState([]);

    useEffect(() => {
      const fetchRecipes = async () => {
        try {
          //const recipesData = await ApiHelper.GetLikedRecipes(User user);
          const recipesData = await ApiHelper.GetRecipes();
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
                {recipes.map(r => (
                    <RecipeCard key={r.name} recipeName={r.name} description={r.description} imageSrc={r.imageSrc}/>
                ))}
            </div>
        </>
    );
}


export default SavedRecipes;
