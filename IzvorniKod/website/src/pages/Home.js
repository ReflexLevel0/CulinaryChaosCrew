import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Home.css';
import Gallery from "../components/Gallery";

function Home() {
  let isLoggedIn = localStorage.getItem("username") === "" ? false : true;
  return (
<div className='main'>
    <div className="gallery-container">
      <Gallery></Gallery>
    </div>
  <div className="welcome-container">
    <h1>Welcome to CookBooked</h1>
    <p>
      Here you will find diverse recipes for delicious dishes. Whether you are
      a beginner in the kitchen or an experienced chef, get inspired and create
      amazing flavors.
    </p>
    {isLoggedIn ? (
        <>
          <Link to="/recipes" className="explore-link">
            Explore Recipes
          </Link>
          <Link to="/saved" className="explore-link">
            Saved Recipes
          </Link>
        </>
      ) : (
        <Link to="/recipes" className="explore-link">
            Explore Recipes
          </Link>
      )}
    </div>
    <div className='table-container'>
    <section className="column">
        <h2>For Unregistered Users</h2>
        <div className="features-list">
        <ul>
          <li>Browse recipes based on categories such as appetizers, desserts, or specific cuisines.</li>
          <li>Explore recipes using particular ingredients.</li>
          </ul>
        </div>
      </section>

      <section className="column">
        <h2>For Registered Users</h2>
        <div className="features-list">
          <h3>Create and Share Recipes</h3>
          <ul>
            <li>Publish your own cooking and baking recipes with essential details like title, ingredients, preparation steps, and cooking time.</li>
            <li>Add tags like "vegetarian" or "gluten-free" to your recipes.</li>
            <li>Include images and videos showcasing the preparation process.</li>
          </ul>

          <h3>Connect with Recipe Authors</h3>
          <ul>
            <li>Engage with recipe authors through messaging, chat, or video calls.</li>
            <li>Schedule communication times with authors.</li>
          </ul>

          <h3>Interact with Recipes</h3>
          <ul>
            <li>Mark, comment on, and save recipes for future reference.</li>
            <li>Follow your favorite recipe authors for updates on their latest creations.</li>
          </ul>

          <h3>Profile Management</h3>
          <ul>
            <li>Public Profiles: Showcase your published recipes, followers, and the authors you follow.</li>
            <li>Private Profiles: Manage personal information, communication settings, and notifications.</li>
          </ul>
        </div>
      </section>

      <section className="column">
        <h2>Additional Features</h2>
        <div className="features-list">
          <h3>Communication Features</h3>
          <ul>
            <li>Recipe authors can set availability times for communication.</li>
            <li>System administrators maintain and manage the platform.</li>
          </ul>
        </div>
      </section>

  </div>
</div>
  );
}

export default Home;
