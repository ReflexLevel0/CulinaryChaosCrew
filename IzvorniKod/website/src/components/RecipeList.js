import React, { useState, useEffect } from 'react';
import RecipeCard from './RecipeCard';
import ApiHelper from '../ApiHelper';

function RecipeList() {
  const [recipes, setRecipes] = useState([]);

  useEffect(() => {
    const fetchRecipes = async () => {
      try {
        const recipesData = await ApiHelper.GetRecipes();
        setRecipes(recipesData);
        console.log(recipesData)
      } catch (error) {
        console.error('Error fetching recipes:', error);
      }
    };
    fetchRecipes();
  }, []);
  return (
    <div className="recipe-cards-container">
      {recipes.slice().reverse().map((recipe) => {
        const maxDescriptionLength = 100;
        console.log(recipe.instructions)
        const truncatedDescription =
          recipe.instructions.length > maxDescriptionLength
            ? `${recipe.instructions.slice(0, maxDescriptionLength)}...`
            : recipe.instructions;

        return (
          <RecipeCard
            key={recipe.rid}
            rid={recipe.rid}
            recipeName={recipe.name}
            description={truncatedDescription}
            imageSrc={recipe.iurl}
          />
        );
      })}
    </div>
  );
}

export default RecipeList;
