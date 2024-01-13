import React, { useState } from 'react';
import '../styles/RecipeForm.css';

const RecipeForm = ({ onSubmit }) => {
  const [name, setName] = useState('');
  const [category, setCategory] = useState('');
  const [ingredients, setIngredients] = useState('');
  const [origin, setOrigin] = useState('');
  const [tags, setTags] = useState('');
  const [url, setUrl] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    alert(`Recipe ${name} created`);
    const recipeData = {
      name,
      category,
      ingredients,
      origin,
      tags,
      url,
    };

    onSubmit(recipeData);
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Name:
        <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
      </label>
      <br />
      <label>
        Category:
        <input type="text" value={category} onChange={(e) => setCategory(e.target.value)} />
      </label>
      <br />
      <label>
        Ingredients:
        <textarea value={ingredients} onChange={(e) => setIngredients(e.target.value)} />
      </label>
      <br />
      <label>
        Origin:
        <input type="text" value={origin} onChange={(e) => setOrigin(e.target.value)} />
      </label>
      <br />
      <label>
        Tags:
        <input type="text" value={tags} onChange={(e) => setTags(e.target.value)} />
      </label>
      <br />
      <label>
        URL:
        <input type="text" value={url} onChange={(e) => setUrl(e.target.value)} />
      </label>
      <br />
      <button id='submit' type="submit">Create Recipe</button>
    </form>
  );
};

export default RecipeForm;