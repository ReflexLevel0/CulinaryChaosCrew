import React, { useState, useEffect } from 'react';
import ApiHelper from '../ApiHelper';
import RecipeCard from './RecipeCard';
import { useParams } from 'react-router-dom';
import '../styles/OtherProfile.css';

const OtherProfile = () => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [userRecipes, setUserRecipes] = useState([]);
  const { username: paramUsername } = useParams();

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const response = await ApiHelper.ProfileByUsername(paramUsername);

        if (response.ok) {
          const userData = await response.json();
          setUser(userData);
        } else {
          const errorData = await response.json();
          console.error(errorData.message);
        }
      } catch (error) {
        console.error('Error fetching profile:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchProfile();
  }, [paramUsername]); // Use paramUsername in the dependency array

  useEffect(() => {
    const fetchRecipes = async () => {
      try {
        const uid = await ApiHelper.GetUIDFromUsername(paramUsername);
        const recipesData = await ApiHelper.GetRecipesForUser(uid);
        setUserRecipes(recipesData);
        console.log(uid);
      } catch (error) {
        console.error('Error fetching recipes:', error);
      }
    };

    fetchRecipes();
  }, [paramUsername]); // Add paramUsername to the dependency array

  

  return (
    <div>
      {loading ? (
        <p>Loading...</p>
      ) : user ? (
        <div className="user-profile-container">
          <div className="user-info">
            <img className="author-photo" src={"https://www.pngkey.com/png/full/202-2024691_my-profile-comments-my-profile-icon-png.png"} alt="Author" />
            <p className="user-info-item">
              <span className="info-label">Username:</span> {user.username}
            </p>
            <p className="user-info-item">
              <span className="info-label">Name:</span> {user.name}
            </p>
            <p className="user-info-item">
              <span className="info-label">Surname:</span> {user.surname}
            </p>
            <p className="user-info-item">
              <span className="info-label">Email:</span> {user.email}
            </p>
            <p className="user-info-item">
              <span className="info-label">Age:</span> {user.age}
            </p>
          </div>
          <h1>{paramUsername}'s recipes:</h1>
          <div className="recipe-cards-container">
            {userRecipes.map((recipe) => (
              <RecipeCard

                rid={recipe.rid}
                recipeName={recipe.name}
                description={recipe.instructions}
                imageSrc={recipe.iurl}
              />
            ))}
          </div>
        </div>
      ) : (
        <p>No user data available.</p>
      )}
    </div>
  );
};

export default OtherProfile;
