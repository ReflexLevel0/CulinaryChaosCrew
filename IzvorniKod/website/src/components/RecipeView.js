import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import '../styles/RecipeView.css';
import ApiHelper from '../ApiHelper';

function RecipeView({recipe}) {
    const [username, setUsername] = useState(null);
    const [likes, setLikes] = useState(0);


    useEffect(() => {
        const fetchUsername = async () => {
            try {
                if(recipe.uid !== undefined){
                    const fetchedUsername = await ApiHelper.GetUsernameFromUID(recipe.uid);
                    setUsername(fetchedUsername);
                }
            } catch (error) {
                // Handle errors if needed
                console.error("Error fetching username:", error);
            }
        };

        const fetchLikes = async () => {
            try {
                if(recipe.rid !== undefined){
                    const fetchedLikes = await ApiHelper.getLikesForRecipe(recipe.rid);
                    setLikes(fetchedLikes);
                }
            } catch (error) {
                // Handle errors if needed
                console.error("Error fetching likes:", error);
            }
        };

        fetchUsername();
        fetchLikes();
    }, [recipe.uid]);


    return (
        <div className="recipe-view-container">
            <div className="recipe-content">
                <div className="recipe-text">
                    <h2>{recipe.name}</h2>
                    <div className="recipe-details">
                        <div>
                            <p><strong>Category:</strong> {recipe.category}</p>
                            <p><strong>Ingredients:</strong> {recipe.ingredients}</p>
                            <p><strong>Instructions:</strong> {recipe.instructions}</p>
                        </div>
                        <div>
                            <p><strong>Origin:</strong> {recipe.origin}</p>
                            <p><strong>Preparation time:</strong> {recipe.preptime}</p>
                            <p><strong>Tags:</strong> {recipe.tags}</p>
                            <p><strong>Likes:</strong> {likes}</p>

                        </div>
                        <div>
                        </div>
                    </div>
                </div>
                <div className="recipe-image">
                    <img src={recipe.iurl} alt=""/>
                </div>
            </div>
            <div className="video-container">
            <iframe width="1228" height="691" src={recipe.vurl} title={recipe.name} frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
            </div>

            <div className="author-div">
            {username !== null ? (
                <div className="author-container">
                <img className="author-photo" src={"https://www.pngkey.com/png/full/202-2024691_my-profile-comments-my-profile-icon-png.png"} alt="Author" />
                <div className="author-info">
                <Link to={`/user/${username}`} rel="noopener noreferrer">
                <p>Author: {username}</p>
                </Link>
                </div>
            </div>
            ) : (
             <p className="loading-text">Loading author...</p>
            )}
            </div>
           
        </div>
    );
}

export default RecipeView;
