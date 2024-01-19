import '../styles/RecipeCardDesktop.css'
import { Link } from 'react-router-dom'

function RecipeCard({recipeName, description, imageSrc}){
    return(
        <div>
        <h3>{recipeName}</h3>
        <Link to={`/recipe/${recipeName}`}>
          <div className="descriptionContainer">
            <img src={imageSrc} alt="" />
            <p>{description}</p>
          </div>
        </Link>
      </div>
    
    )
}

export default RecipeCard;