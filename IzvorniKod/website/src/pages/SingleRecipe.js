import React, { useState, useEffect } from 'react';
import RecipeView from '../components/RecipeView';
import '../styles/SingleRecipeView.css';
import ApiHelper from '../ApiHelper';



function SingleRecipe() {
    const [recipe1, setRecipe] = useState([]);

    const currentPath = window.location.pathname;
    const pathParts = currentPath.split('/');
    const rid = pathParts[pathParts.length - 1].split("=")[1];

    useEffect(() => {
      const fetchRecipe = async () => {
        try {
          const recipesData = await ApiHelper.GetRecipebyRid(rid);
          setRecipe(recipesData);
        } catch (error) {
          console.error('Error fetching recipes:', error);
        }
      };
  
      fetchRecipe();
    }, [rid]);

    
    return (
      <div className="recipe-details-page">
        <h1>Recipe information</h1>
        <RecipeView recipe={recipe1} />
        <div className="like-button">
          <button>
            Like
          </button>
          <p> Likes</p>
        </div>
      </div>
    );
  }
  
  export default SingleRecipe;
