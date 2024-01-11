import React from 'react';
import RecipeForm from '../components/RecipeForm';

const CreateRecipePage = () => {
  const handleCreateRecipe = (recipeData) => {
    fetch('YOUR_BACKEND_API_ENDPOINT', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(recipeData),
    })
      .then(response => response.json())
      .then(data => {
        console.log('Recipe created successfully:', data);
      })
      .catch(error => {
        console.error('Error creating recipe:', error);
      });
  };

  return (
    <div>
      <h1>Create Recipe</h1>
      <RecipeForm onSubmit={handleCreateRecipe} />
    </div>
  );
};

export default CreateRecipePage;