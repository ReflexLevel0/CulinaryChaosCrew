import React from 'react';
import { useParams } from 'react-router-dom';
import Profile from '../components/Profile'; 

const ProfilePage = ({ users }) => {
  const { username } = useParams();
  const user = users.find(u => u.username === username);

  if (!user) {
    return <div>User not found</div>;
  }

  return (
    <div>
      <h1>User Profile</h1>
      <Profile user={user} />
    </div>
  );
};

export default ProfilePage;
