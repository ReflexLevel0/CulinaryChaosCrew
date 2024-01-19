import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Search.css';

const Search = ({ onSearchRecipe, onSearchProfile }) => {
  const navigate = useNavigate();
  const [searchTerm, setSearchTerm] = useState('');
  const [filterType, setFilterType] = useState('recipes');

  const handleSearch = () => {
    navigate(`/search?term=${searchTerm}&type=${filterType}`);
  };

  const getPlaceholderText = () => {
    return filterType === 'recipes' ? 'Search for recipes...' : 'Search for profiles...';
  };

  return (
    <div className="searchContainer">
      <div className='upper'>
        <input
          type="text"
          className="searchInput"
          placeholder={getPlaceholderText()}
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
        <button className="searchButton" onClick={handleSearch}>
          Search
        </button>
      </div>
      <div className="filterButtons">
        <button
          className={`filterButton ${filterType === 'recipes' && 'active'}`}
          onClick={() => setFilterType('recipes')}
        >
          Recipes
        </button>
        <button
          className={`filterButton ${filterType === 'profiles' && 'active'}`}
          onClick={() => setFilterType('profiles')}
        >
          Profiles
        </button>
      </div>
    </div>
  );
};

export default Search;
