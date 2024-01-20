// SearchResultsPage.js
import React, { useState, useEffect } from 'react';
import {Link, useLocation} from 'react-router-dom';
import ApiHelper from '../ApiHelper';
import RecipeCard from '../components/RecipeCard';

const SearchResultsPage = () => {
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const term = searchParams.get('term');
  const type = searchParams.get('type');

  const [searchResults, setSearchResults] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);

      try {
        let results;

        if (type === 'recipes') {
          const response = await ApiHelper.searchRecipe(term);
          if (response.ok) {
            results = await response.json();
          } else {
            console.error('Error fetching recipe search results:', response.statusText);
          }
        } else if (type === 'profiles') {
          const response = await ApiHelper.searchProfile(term);
          if (response.ok) {
            results = await response.json();
          } else {
            console.error('Error fetching profile search results:', response.statusText);
          }
        }

        // Ensure that results is an array
        setSearchResults(Array.isArray(results) ? results : []);
      } catch (error) {
        console.error('Error fetching search results:', error);
        // Handle errors, e.g., show an error message
      }

      setLoading(false);
    };

    fetchData();
  }, [term, type]);

  return (
    <div>
      <h2>Search Results</h2>
      <p>Search Term: {term}</p>
      <p>Filter Type: {type}</p>

      {loading ? (
        <p>Loading...</p>
      ) : (
        <div>
          {type === 'recipes' && (
            <div>
              <h3>Recipe Results</h3>
              {searchResults.map((recipe) => (
                <RecipeCard
                key={recipe.rid}
                rid={recipe.rid}
                recipeName={recipe.name}
                description={recipe.desc}
                imageSrc={recipe.iurl}
              />
              ))}
            </div>
          )}

          {type === 'profiles' && (
            <div>
              <h3>Profile Results</h3>
              {searchResults.map((profile, index) => (
                <div key={index}>
                  {/* Display profile information as needed */}
                  <Link to={`/user/${profile.username}`}>
                    <p>{profile.username}</p>
                  </Link>
                </div>
              ))}
            </div>
          )}
        </div>
      )}
    </div>
  );
};

export default SearchResultsPage;
