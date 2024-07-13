import React, { useEffect, useState } from 'react';

const Profile = () => {
  const [profileData, setProfileData] = useState(null);

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const url = 'http://localhost:8080/api/userprofiles/profile';
        const params = {
          emailId: 'Jayaseelan@triveniturbines.com',
          userId: '5'
        };

        const response = await fetch(`${url}?${new URLSearchParams(params)}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
            // Add other headers if needed (e.g., authorization token)
          },
          // If you need to send a body with JSON data:
          // body: JSON.stringify({ /* your request body */ })
        });

        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        
        const data = await response.json();
        setProfileData(data);
      } catch (error) {
        console.error('Error fetching profile:', error);
        // Handle error state or display an error message to the user
      }
    };

    fetchProfile();
  }, []);

  return (
    <div>
      {profileData && (
        <div>
          <p>User ID: {profileData.userId}</p>
          <p>Employee ID: {profileData.employeeId}</p>
          <p>Name: {profileData.employeeName}</p>
          <p>Email: {profileData.emailId}</p>
          <p>Contact Number: {profileData.contactNumber}</p>
          <p>Active/Inactive: {profileData.activeStatus ? 'Active' : 'Inactive'}</p>
          {/* Display other profile fields */}
        </div>
      )}
    </div>
  );
};

export default Profile;
