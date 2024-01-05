import React from 'react';
import '../styles/Error.css';

const Error = () => {
    return (
        <div className="error-container">
          <p className="error-message">404 - Not Found</p>
          <p className="error-description">Oops! The page you are looking for might be under construction or doesn't exist.</p>
          <a href="/" className="error-link">Go back to the home page</a>
        </div>
      );
};

export default Error;
