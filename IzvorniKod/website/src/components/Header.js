import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Header.css';
import Search from './Search';
import ApiHelper from '../ApiHelper';

function Header({ username, loggedIn, signedOut }) {
  const navigate = useNavigate();

  const handleSearchRecipe = async (searchTerm) => {
    try {
      const searchResults = await ApiHelper.searchRecipe(searchTerm);
      // Handle the search results for recipes (e.g., display results or update state)
      console.log('Recipe search results:', searchResults);
      // Navigate to the search results page or perform any other necessary action
      navigate(`/search?term=${searchTerm}&type=recipes`);
    } catch (error) {
      console.error('Error searching recipe:', error);
    }
  };

  const handleSearchProfile = async (searchTerm) => {
    try {
      const searchResults = await ApiHelper.searchProfile(searchTerm);
      // Handle the search results for profiles (e.g., display results or update state)
      console.log('Profile search results:', searchResults);
      // Navigate to the search results page or perform any other necessary action
      navigate(`/search?term=${searchTerm}&type=profiles`);
    } catch (error) {
      console.error('Error searching profile:', error);
    }
  };

  return (
    <>
      <div className="headerWrapper">
        <button className="btn" onClick={() => window.location.href = '/'}>
          <img src="../../images/CookBooked_logo.png" alt="Logo" id="logo_img" />
        </button>
        <div className="centerContent">
          <Search
            onSearchRecipe={handleSearchRecipe}
            onSearchProfile={handleSearchProfile}
          />
        </div>
        <div className="rightButtonWrapper">
          {loggedIn ? (
            <>
              <a href="/profile" className="profileLinkButton">
                <img
                  src="https://www.pngkey.com/png/full/202-2024691_my-profile-comments-my-profile-icon-png.png"
                  alt="Profile"
                  className="profileIcon"
                />
                <div>
                  <span className="usernameText">{username}</span>
                </div>
              </a>
              <button className="btn" onClick={signedOut}>
                SIGN OUT
              </button>
            </>
          ) : (
            <>
              <button
                className="btn"
                onClick={() => window.location.href = '/login'}
              >
                LOGIN
              </button>
              <button
                className="btn"
                onClick={() => window.location.href = '/signup'}
              >
                SIGN UP
              </button>
            </>
          )}
        </div>
      </div>
    </>
  );
}

export default Header;
