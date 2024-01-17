import React from 'react';
import '../styles/RecipeView.css';




function RecipeView({recipe}) {
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
                            <p><strong>Tags:</strong> {recipe.tags.join(', ')}</p>
                            <p><strong>Likes:</strong> {recipe.likes}</p>
                        </div>
                        <iframe
                        title="YouTube Video"
                        width="560"
                        height="315"
                        src={recipe.vurl}
                        frameBorder="0"
                        allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                        allowFullScreen
                        />
                    </div>
                </div>
                <div className="recipe-image">
                    <img src={recipe.iurl} alt=""/>
                </div>
            </div>
        </div>
    );
}

export default RecipeView;
