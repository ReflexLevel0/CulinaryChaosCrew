import React, { useState } from 'react';
import '../styles/RecipeForm.css';
import ApiHelper from '../ApiHelper';

const RecipeForm = ( ) => {
  const [name, setName] = useState('');
  const [category, setCategory] = useState('');
  const [ingr, setIngredients] = useState('');
  const [instr, setInstructions] = useState('');
  const [origin, setOrigin] = useState('');
  const [tags, setTags] = useState('');
  const [iurl, setIUrl] = useState('');
  const [vurl, setVUrl] = useState('');
  const [preptime, setPreptime] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    const recipeData = {name, category, ingr, instr, origin, tags, iurl, vurl, preptime};
    console.log(recipeData)
    ApiHelper.CreateRecipe(name, category, ingr, instr, origin, tags, iurl, vurl, preptime).then(response => {
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
        <textarea value={ingr} onChange={(e) => setIngredients(e.target.value)} />
      </label>
      <br />
      <label>
        Instructions:
        <textarea value={instr} onChange={(e) => setInstructions(e.target.value)} />
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
        Image URL:
        <input type="text" value={iurl} onChange={(e) => setIUrl(e.target.value)} />
      </label>
      <br />
      <label>
        Video URL:
        <input type="text" value={vurl} onChange={(e) => setVUrl(e.target.value)} />
      </label>
      <br />
      <label>
        Preparation time:
        <input type="text" value={preptime} onChange={(e) => setPreptime(e.target.value)} />
      </label>
      <br />
      <button id='submit' type="submit" onClick={handleSubmit}>Create Recipe</button>
    </form>
  );
};

export default RecipeForm;