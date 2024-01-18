import React from 'react';
import '../styles/RecipeView.css';
import ApiHelper from '../ApiHelper';

function RecipeView({recipe}) {
    /*const getUsername = async () => {
        try {
          console.log(recipe)
          const username = await ApiHelper.GetUsernameFromUID(recipe.uid);
          return username;
        } catch (error) {
          // Handle errors if needed
          console.error("Error fetching username:", error);
          return null;
        }
      };*/

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
                            <p><strong>Likes:</strong> {recipe.likes}</p>
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
           
        </div>
    );
}

export default RecipeView;
