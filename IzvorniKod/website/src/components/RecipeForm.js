// RecipeForm.js
import React, { useState } from 'react';

const RecipeForm = ({ onSubmit }) => {
  const [title, setTitle] = useState('');
  const [ingredients, setIngredients] = useState('');
  const [instructions, setInstructions] = useState('');
  const [category, setCategory] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    
    const recipeData = {
      title,
      ingredients,
      instructions,
      category,
    };

    onSubmit(recipeData);
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Title:
        <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} />
      </label>
      <br />
      <label>
        Ingredients:
        <textarea value={ingredients} onChange={(e) => setIngredients(e.target.value)} />
      </label>
      <br />
      <label>
        Instructions:
        <textarea value={instructions} onChange={(e) => setInstructions(e.target.value)} />
      </label>
      <br />
      <label>
        Category:
        <input type="text" value={category} onChange={(e) => setCategory(e.target.value)} />
      </label>
      <br />
      <button type="submit">Create Recipe</button>
    </form>
  );
};

export default RecipeForm;
