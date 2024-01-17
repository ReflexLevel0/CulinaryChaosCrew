import React, { useState } from 'react';
import '../styles/RecipeForm.css';
import ApiHelper from '../ApiHelper';

const RecipeForm = ( ) => {
  const [name, setName] = useState('');
  const [category, setCategory] = useState('');
  const [ingredients, setIngredients] = useState('');
  const [origin, setOrigin] = useState('');
  const [tags, setTags] = useState('');
  const [url, setUrl] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    const recipeData = {
      name,
      category,
      ingredients,
      origin,
      tags,
      url,
    };
    console.log(recipeData)
    ApiHelper.CreateRecipe(name, category, ingredients, origin, tags, url).then(response => {
      //If recipe creation is successful
      if(response.status === 200){
        alert(`Recipe ${name} created succesfully`)
        window.location.href = '/create'
      }
      //If recipe creation  failed
      else{
          response.json().then(json => alert(json.message))
      }
  })
  };

  return (
    <form>
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
      <button id='submit' type="submit" onClick={handleSubmit}>Create Recipe</button>
    </form>
  );
};

export default RecipeForm;