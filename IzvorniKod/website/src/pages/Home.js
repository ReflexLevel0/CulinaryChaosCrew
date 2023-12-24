import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Home.css';

function Home() {
  return (
    <div className="welcome-container">
      <h1>Dobrodošli na Kulinarski Kutak</h1>
      <p>
        Ovdje ćete pronaći raznolike recepte za ukusna jela. Bez obzira jeste li
        početnik u kuhinji ili iskusni kuhar, inspirirajte se i stvarajte
        nevjerojatne okuse.
      </p>
      <Link to="/recipes" className="explore-link">
        Istraži Recepte
      </Link>
      <Link to="/saved" className="explore-link">
        Spremljeni recepti
      </Link>
    </div>
  );
}

export default Home;
