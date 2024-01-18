import React, { useState, useEffect } from 'react';
import ApiHelper from '../ApiHelper';

const Profile = () => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchProfile = async () => {
            try {
                const response = await ApiHelper.ProfileByUsername(localStorage.getItem('username'));

                if (response.ok) {
                    const userData = await response.json();
                    setUser(userData);
                } else {
                    const errorData = await response.json();
                    console.error(errorData.message);
                }
            } catch (error) {
                console.error('Error fetching profile:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchProfile();
    }, []);

    const handleDeleteProfile = async () => {
        const uid = localStorage.getItem('uid');
        const result = await ApiHelper.deleteProfile(uid);

        if (result) {
            console.log('Profile deleted successfully');
            // Optionally, you can redirect the user or perform other actions after deletion.
        } else {
            console.error('Failed to delete profile');
        }

        localStorage.removeItem('username');
        localStorage.removeItem('uid');
        setUser(null);
    };

    // console.log(user)
    return (
        <div>
            {loading ? (
                <p>Loading...</p>
            ) : user ? (
                <>
                    <div className="container">
                        <div className="row">
                            <div className="col-12">
                                <div className="my-5">
                                    <h3>My Profile</h3>
                                    <hr/>
                                </div>
                                <form className="file-upload">
                                    <div className="row mb-5 gx-5">
                                        <div className="col-xxl-8 mb-5 mb-xxl-0">
                                            <div className="bg-secondary-soft px-4 py-5 rounded">
                                                <div className="row g-3">

                                                    <div className="col-md-6">
                                                        <label className="form-label">First Name</label>
                                                        <input type="text" className="form-control" placeholder=""
                                                               aria-label="First name" value={user.name}/>
                                                    </div>

                                                    <div className="col-md-6">
                                                        <label className="form-label">Last Name</label>
                                                        <input type="text" className="form-control" placeholder=""
                                                               aria-label="Last name" value={user.surname}/>
                                                    </div>
                                                    <div className="col-md-6">
                                                        <label htmlFor="inputEmail4" className="form-label">Email</label>
                                                        <input type="email" className="form-control" id="inputEmail4"
                                                               value={user.email}/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="row mb-5 gx-5">
                                        <div className="col-xxl-6">
                                            <div className="bg-secondary-soft px-4 py-5 rounded">
                                                <div className="row g-3">
                                                    <h4 className="my-4">Change Password</h4>

                                                    <div className="col-md-6">
                                                        <label htmlFor="exampleInputPassword1" className="form-label">Old password</label>
                                                        <input type="password" className="form-control"
                                                               id="exampleInputPassword1"/>
                                                    </div>

                                                    <div className="col-md-6">
                                                        <label htmlFor="exampleInputPassword2" className="form-label">New password</label>
                                                        <input type="password" className="form-control"
                                                               id="exampleInputPassword2"/>
                                                    </div>

                                                    <div className="col-md-12">
                                                        <label htmlFor="exampleInputPassword3" className="form-label">Confirm Password</label>
                                                        <input type="password" className="form-control"
                                                               id="exampleInputPassword3"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="delete_update_profile_btns">
                                        <button type="button" className="btn btn-danger btn-lg" onClick={handleDeleteProfile}>Delete profile</button>
                                        <button type="button" className="btn btn-primary btn-lg">Update profile</button>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </>
            ) : (
                <p>No user data available.</p>
            )}
        </div>
    );
};

export default Profile;
