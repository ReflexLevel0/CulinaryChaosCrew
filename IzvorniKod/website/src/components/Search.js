import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Search.css';

const Search = ({ allRecipes }) => {
  const navigate = useNavigate();
  const [searchTerm, setSearchTerm] = useState('');
  const [filterType, setFilterType] = useState('recipes');

  const handleSearch = () => {
    const matchingRecipes = allRecipes.filter(recipe =>
      recipe.name.toLowerCase().includes(searchTerm.toLowerCase())
    );

    navigate(`/search?term=${searchTerm}&type=${filterType}`, {
      state: { searchResults: matchingRecipes },
    });
  };

  return (
    <div className="searchContainer">
      <div className='upper'>
        <input
        type="text"
        className="searchInput"
        placeholder="Search for recipes or profiles..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}/>
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
