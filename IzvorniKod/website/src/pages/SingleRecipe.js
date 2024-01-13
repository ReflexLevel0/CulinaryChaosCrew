import React, { useState, useEffect } from 'react';
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
    const [liked, setLiked] = useState(false);
    const [likes, setLikes] = useState(sampleRecipe.likes);
  
    useEffect(() => {
      if (liked) {
        fetch('your_backend_api_url', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({  userId: 0, recipeId: sampleRecipe.id, liked: true }),
        })
          .then(response => response.json())
          .then(data => {
            alert('Liked!', data);
          })
          .catch(error => {
            console.error('Error:', error);
          });
      } else {
        fetch('your_backend_api_url', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify({ userId: 0, recipeId: sampleRecipe.id, liked: false}),
          })
            .then(response => response.json())
            .then(data => {
              alert('Disliked!', data);
            })
            .catch(error => {
              console.error('Error:', error);
            });
      }
    }, [liked]);
  
    const handleLike = () => {
      setLiked(!liked);
      setLikes(liked ? likes - 1 : likes + 1);
    };
  
    return (
      <div className="recipe-details-page">
        <h1>Recipe information</h1>
        <RecipeView {...sampleRecipe} />
        <div className="like-button">
          <button className={liked ? 'liked' : ''} onClick={handleLike}>
            Like
          </button>
          <p>{likes} Likes</p>
        </div>
      </div>
    );
  }
  
  export default SingleRecipe;
