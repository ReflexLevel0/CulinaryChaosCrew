import React from 'react';
import ApiHelper from '../ApiHelper';

const Profile = () => {
  let username = localStorage.getItem('username')
  let user;
    ApiHelper.ProfileByUsername(username).then(response => {
        if(response.status === 200){
            user = response;
        }
        else{
            response.json().then(json => alert(json.message))
        }
  })

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