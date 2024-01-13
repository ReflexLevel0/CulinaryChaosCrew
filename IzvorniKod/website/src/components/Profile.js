import React, { useState, useEffect } from 'react';

const Profile = ({ match }) => {
  const [user, setUser] = useState(null);
  const { username } = match.params;

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await fetch(`https://backend/profile/${username}`);
        const userData = await response.json();
        setUser(userData);
      } catch (error) {
        console.error('Error fetching user:', error);
      }
    };

    fetchUser();
  }, [username]);

  return (
    <div>
      {user ? (
        <>
          <h1>Welcome to your profile!</h1>
          <h2>Here are some of your personal details:</h2>
          <ul>
            <li>Username: {user.username}</li>
            <li>Email: {user.email}</li>
          </ul>
        </>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
};

export default Profile;