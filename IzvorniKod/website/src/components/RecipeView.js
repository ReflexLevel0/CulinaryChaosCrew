import React from 'react';
import '../styles/RecipeView.css';

function RecipeView({ name, category, ingr, instr, origin, tags, url, likes }) {
    return (
        <div className="recipe-view-container">
            <div className="recipe-content">
                <div className="recipe-text">
                    <h2>{name}</h2>
                    <div className="recipe-details">
                        <div>
                            <p><strong>Category:</strong> {category}</p>
                            <p><strong>Ingredients:</strong> {ingr}</p>
                            <p><strong>Instructions:</strong> {instr}</p>
                        </div>
                        <div>
                            <p><strong>Origin:</strong> {origin}</p>
                            <p><strong>Tags:</strong> {tags.join(', ')}</p>
                            <p><strong>Likes:</strong> {likes}</p>
                        </div>
                    </div>
                </div>
                <div className="recipe-image">
                    <img src={url} alt=""/>
                </div>
            </div>
        </div>
    );
}

export default RecipeView;
