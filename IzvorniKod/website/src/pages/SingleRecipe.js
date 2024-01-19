import React, { useState, useEffect } from 'react';
import RecipeView from '../components/RecipeView';
import '../styles/SingleRecipeView.css';
import ApiHelper from '../ApiHelper';
import CommentList from "../components/CommentList";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faThumbsUp} from "@fortawesome/free-solid-svg-icons";


function SingleRecipe({loggedIn}) {

    const uid = localStorage.getItem("uid");

    const [recipe1, setRecipe] = useState([]);

    const currentPath = window.location.pathname;
    const pathParts = currentPath.split('/');
    const rid = pathParts[pathParts.length - 1].split("=")[1];

    const liked = ApiHelper.isRecipeLikedByUser(uid, rid)
    // console.log(liked)
    const [isLiked, setIsLiked] = useState((uid ? liked : false));

    const handleLikeClick = (e) => {
        e.preventDefault();
        if (isLiked) {
            ApiHelper.UnlikeRecipe(rid, uid).then(response => {
                if(response.status === 200){
                    setIsLiked(false)
                    console.log('Successfully unliked recipe')
                }
                else{
                    response.json().then(json => alert(json.message))
                }
            })
        } else {
            ApiHelper.LikeRecipe(rid, uid).then(response => {
                if(response.status === 200) {
                    setIsLiked(true)
                    console.log('Successfully liked recipe')
                }
                else{
                    response.json().then(json => alert(json.message))
                }
            })
        }
    };

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
            {loggedIn ? (
                <div className="like-button">
                    <button
                        id="submit"
                        type="submit"
                        onClick={handleLikeClick}
                        style={{
                            background: 'none',
                            border: 'none',
                            cursor: 'pointer',
                            color: isLiked ? 'blue' : 'grey'
                        }}
                    >
                        <FontAwesomeIcon icon={faThumbsUp} style={{color: isLiked ? 'blue' : 'grey'}}/>
                        <span className="icon">{isLiked ? 'Unlike' : 'Like'}</span>
                    </button>
                </div>
            ) : null}
            <CommentList rid={rid} loggedIn={loggedIn}/>
        </div>
    );
}

export default SingleRecipe;
