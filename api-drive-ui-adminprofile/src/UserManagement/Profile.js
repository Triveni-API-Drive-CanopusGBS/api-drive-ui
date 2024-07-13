
import React, { useEffect, useState } from 'react';
import { fetchProfile } from  './userProfileService';

const ProfilePage = () => {
    const [profileData, setProfileData] = useState(null);
    const emailId = 'Jayaseelan@triveniturbines.com'; // Replace with actual emailId
    const userId = '5'; // Replace with actual userId

    useEffect(() => {
        const fetchUserProfile = async () => {
            try {
                const data = await fetchProfile(emailId, userId);
                setProfileData(data);
            } catch (error) {
                console.error('Error fetching profile:', error.message);
            }
        };

        fetchUserProfile();
    }, [emailId, userId]);

    return (
        <div>
            <h2>User Profile</h2>
            {profileData ? (
                <div>
                    <p>User ID: {profileData.userId}</p>
                    <p>EmailId:{profileData.emailId}</p>
                    
                    {/* Render other profile data as needed */}
                </div>
            ) : (
                <p>Loading profile...</p>
            )}
        </div>
    );
};

export default ProfilePage;
