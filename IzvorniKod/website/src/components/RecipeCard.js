import '../styles/RecipeCardDesktop.css'

function RecipeCard({recipeName, description, imageSrc}){
    return(
    <div>
        <h3>{recipeName}</h3>
        <div className="descriptionContainer">
            <img src={imageSrc} alt=""/>
            <p>{description}</p>
        </div>
    </div>
    )
}

export default RecipeCard;