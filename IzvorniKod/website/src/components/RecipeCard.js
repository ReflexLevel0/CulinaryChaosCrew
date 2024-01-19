import '../styles/RecipeCardDesktop.css'
import { Link } from 'react-router-dom'
import {useState} from "react";
import ApiHelper from "../ApiHelper";

function RecipeCard({rid, recipeName, description, imageSrc}){

    const uid = localStorage.getItem("uid");

    const [isLiked, setIsLiked] = useState(uid ? ApiHelper.isRecipeLikedByUser(rid, uid) : false);

    const handleLikeClick = () => {
        setIsLiked((prevIsLiked) => !prevIsLiked);
        if (isLiked){
            ApiHelper.likeRecipe(rid, uid);
        }
        else{
            ApiHelper.unlikeRecipe(rid, uid);
        }
    };

    return(
        <div>
        <h3>
            {recipeName}
            {uid && (
                <button onClick={handleLikeClick} className="like-icon">
                    {isLiked ? '❤️' : '🤍'}
                </button>
            )}
        </h3>
        <Link to={`/recipe/rid=${rid}`}>
          <div className="descriptionContainer">
            <img src={imageSrc} alt="recipe" />
            <p><h4> Description: </h4>{description}</p>
          </div>
        </Link>
      </div>
    
    )
}

export default RecipeCard;