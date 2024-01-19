import '../styles/RecipeCardDesktop.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faThumbsUp } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import ApiHelper from '../ApiHelper';

function RecipeCard({ rid, recipeName, description, imageSrc }) {
    const uid = localStorage.getItem('uid');

    const [isLiked, setIsLiked] = useState(false);

    useEffect(() => {
        const fetchLikeStatus = async () => {
            if (uid) {
                try {
                    const liked = await ApiHelper.isRecipeLikedByUser(uid, rid);
                    setIsLiked(liked);
                } catch (error) {
                    console.error('Error fetching like status:', error);
                }
            }
        };

        fetchLikeStatus();
    }, [uid, rid]);

    const handleLikeClick = async (e) => {
        e.preventDefault();

        try {
            if (isLiked) {
                await ApiHelper.UnlikeRecipe(rid, uid);
                setIsLiked(false);
                console.log('Successfully unliked recipe');
            } else {
                await ApiHelper.LikeRecipe(rid, uid);
                setIsLiked(true);
                console.log('Successfully liked recipe');
            }
        } catch (error) {
            console.error('Error handling like click:', error);
        }
    };

    return (
        <div>
            <h3>
                {recipeName}
                {uid && (
                    <div className="like-button">
                        <button
                            id="submit"
                            type="submit"
                            onClick={handleLikeClick}
                            style={{ background: 'none', border: 'none', cursor: 'pointer', color: isLiked ? 'blue' : 'grey' }}
                        >
                            <FontAwesomeIcon icon={faThumbsUp} style={{ color: isLiked ? 'blue' : 'grey' }} />
                            <span className="icon">{isLiked ? 'Unlike' : 'Like'}</span>
                        </button>
                    </div>
                )}
            </h3>
            <div className="recipe-details">
                <Link to={`/recipe/rid=${rid}`}>
                    <div className="descriptionContainer">
                        <img src={imageSrc} alt="recipe" />
                        <div>
                            <h4>Description:</h4>
                            <p>{description}</p>
                        </div>
                    </div>
                </Link>
            </div>
        </div>
    );
}

export default RecipeCard;
