import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Home.css';
import Gallery from "../components/Gallery";

function Home() {
  return (
    <div className="welcome-container">
      <div>
        <Gallery></Gallery>
      </div>
  <h1>Welcome to CookBooked</h1>
  <p>
    Here you will find diverse recipes for delicious dishes. Whether you are
    a beginner in the kitchen or an experienced chef, get inspired and create
    amazing flavors.
  </p>
  <Link to="/recipes" className="explore-link">
    Explore Recipes
  </Link>
  <Link to="/saved" className="explore-link">
    Saved Recipes
  </Link>
</div>
  );
}

export default Home;
