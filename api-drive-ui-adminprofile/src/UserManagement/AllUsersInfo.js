import React,{useState,useEffect} from 'react';
import {fetchUsersInfo} from "./userProfileService"
import { useNavigate } from 'react-router-dom';


const AllUsersInfo = () => {
    const [usersInfo, setUsersInfo] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchUsersData = async () => {
            try {
                const usersData = await fetchUsersInfo();
                setUsersInfo(usersData); // Assuming usersData is an array of user info objects
                setLoading(false)
            } catch (error) {
                console.error('Error fetching users info:', error.message);
            }
        };

        fetchUsersData();
    }, []);

    const handleRowClick = (userId) => {
      navigate(`/edituser/${userId}`);
    };
  
    if (loading) {
      return <div>Loading...</div>;
    }
  
    if (error) {
      return <div>Error: {error}</div>;
    }
    return (
      <div className="container mt-4">
        <h2>All Users Information</h2>
        <table className="table table-striped table-bordered">
          <thead className="thead-dark">
            <tr>
              <th>User ID</th>
              <th>Employee ID</th>
              <th>Employee Name</th>
              <th>Email ID</th>
              <th>Contact Number</th>
              <th>Group ID</th>
              <th>Department</th>
              <th>Designation</th>
              <th>Active Status</th>
              <th>Roles</th>
              <th>Regions</th>
            </tr>
          </thead>
          <tbody>
          {usersInfo.map(user => (
            <tr key={user.userId} onClick={() => handleRowClick(user.userId)} style={{ cursor: 'pointer' }}>
              <td>{user.userId}</td>
              <td>{user.employeeId}</td>
              <td>{user.employeeName}</td>
              <td>{user.emailId}</td>
              <td>{user.contactNumber}</td>
              <td>{user.groupId}</td>
              <td>{user.department}</td>
              <td>{user.designation}</td>
              <td>{user.activeStatus ? 'Active' : 'Inactive'}</td>
              <td>{user.roleNames.join(', ')}</td>
              <td>{user.regionNames.join(', ')}</td>
            </tr>
          ))}
          </tbody>
        </table>
      </div>
    );
};

export default AllUsersInfo;
