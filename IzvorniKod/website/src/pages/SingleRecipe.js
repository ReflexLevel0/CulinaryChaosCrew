import React, { useState, useEffect } from 'react';
import RecipeView from '../components/RecipeView';
import '../styles/SingleRecipeView.css';
import ApiHelper from '../ApiHelper';
import CommentList from "../components/CommentList";


function SingleRecipe({loggedIn}) {

    const uid = localStorage.getItem("uid");

    const [isLiked, setIsLiked] = useState((uid ? ApiHelper.isRecipeLikedByUser(uid) : false));

    const handleLikeClick = () => {
        setIsLiked((prevIsLiked) => !prevIsLiked);
        if (isLiked){
            ApiHelper.likeRecipe(rid, uid);
        }
        else{
            ApiHelper.unlikeRecipe(rid, uid);
        }
    };

    const [recipe1, setRecipe] = useState([]);

    const currentPath = window.location.pathname;
    const pathParts = currentPath.split('/');
    const rid = pathParts[pathParts.length - 1].split("=")[1];

    useEffect(() => {
      const fetchRecipe = async () => {
        try {
          const recipesData = await ApiHelper.GetRecipebyRid(rid);
          console.log(recipesData)
          setRecipe(recipesData);
        } catch (error) {
          console.error('Error fetching recipes:', error);
        }
      };
  
      fetchRecipe();
    }, [rid]);

    console.log(recipe1)
    return (
      <div className="recipe-details-page">
        <h1>Recipe information</h1>
        <RecipeView recipe={recipe1} />
          {loggedIn &&
            <div className="like-button">
                <button onClick={handleLikeClick}>
                    {isLiked ? '❤️' : '🤍'}
                </button>
            </div>
          }
          <CommentList rid={rid} loggedIn={loggedIn}/>
      </div>
    );
  }
  
  export default SingleRecipe;
