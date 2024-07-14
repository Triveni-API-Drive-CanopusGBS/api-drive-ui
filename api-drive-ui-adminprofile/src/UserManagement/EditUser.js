// EditUserPage.js

import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { updateUserProfilewithId, fetchUserProfilewithId, fetchRoles, fetchRegions } from './userProfileService';
import './usermgmt.css'; // Assuming you have this CSS file for additional styling

const EditUserPage = () => {
  const { id } = useParams();
  const [userData, setUserData] = useState({
    employeeId: '',
    employeeName: '',
    emailId: '',
    contactNumber: '',
    groupId: '',
    modifiedById: '',
    department: '',
    designation: '',
    activeStatus: false,
    roleNames: [],
    regionNames: [],
  });
  const [rolesList, setRolesList] = useState([]);
  const [regionsList, setRegionsList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [user, roles, regions] = await Promise.all([
          fetchUserProfilewithId(id),
          fetchRoles(),
          fetchRegions(),
        ]);
        setUserData(user);
        setRolesList(roles);
        setRegionsList(regions);
        setLoading(false);
      } catch (error) {
        setError(error.message);
        setLoading(false);
      }
    };

    fetchData();
  }, [id]);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setUserData({ ...userData, [name]: value });
  };

  const handleCheckboxChange = (event) => {
    const { name, checked } = event.target;
    setUserData({ ...userData, [name]: checked });
  };

  const handleMultiSelectChange = (event) => {
    const { name, options } = event.target;
    const value = Array.from(options).filter(option => option.selected).map(option => option.value);
    setUserData({ ...userData, [name]: value });
  };

  const handleFormSubmit = async (event) => {
    event.preventDefault();
    try {
      await updateUserProfilewithId(id, userData);
      navigate('/users');
    } catch (error) {
      console.error('Error updating user:', error.message);
    }
  };

  if (loading) {
    return <p>Loading user profile...</p>;
  }

  if (error) {
    return <p>Error: {error}</p>;
  }

  return (
    <div className="container mt-5">
      <h2 className="text-center mb-4">Edit User {id}</h2>
      <form onSubmit={handleFormSubmit}>
        <div className="form-group row">
          <label htmlFor="employeeId" className="col-sm-2 col-form-label">Employee ID</label>
          <div className="col-sm-10">
            <input
              type="text"
              className="form-control"
              id="employeeId"
              name="employeeId"
              value={userData.employeeId}
              onChange={handleInputChange}
            />
          </div>
        </div>
        <div className="form-group row">
          <label htmlFor="employeeName" className="col-sm-2 col-form-label">Employee Name</label>
          <div className="col-sm-10">
            <input
              type="text"
              className="form-control"
              id="employeeName"
              name="employeeName"
              value={userData.employeeName}
              onChange={handleInputChange}
            />
          </div>
        </div>
        <div className="form-group row">
          <label htmlFor="emailId" className="col-sm-2 col-form-label">Email</label>
          <div className="col-sm-10">
            <input
              type="email"
              className="form-control"
              id="emailId"
              name="emailId"
              value={userData.emailId}
              onChange={handleInputChange}
            />
          </div>
        </div>
        <div className="form-group row">
          <label htmlFor="contactNumber" className="col-sm-2 col-form-label">Contact Number</label>
          <div className="col-sm-10">
            <input
              type="text"
              className="form-control"
              id="contactNumber"
              name="contactNumber"
              value={userData.contactNumber}
              onChange={handleInputChange}
            />
          </div>
        </div>
        <div className="form-group row">
          <label htmlFor="department" className="col-sm-2 col-form-label">Department</label>
          <div className="col-sm-10">
            <input
              type="text"
              className="form-control"
              id="department"
              name="department"
              value={userData.department}
              onChange={handleInputChange}
            />
          </div>
        </div>
        <div className="form-group row">
          <label htmlFor="designation" className="col-sm-2 col-form-label">Designation</label>
          <div className="col-sm-10">
            <input
              type="text"
              className="form-control"
              id="designation"
              name="designation"
              value={userData.designation}
              onChange={handleInputChange}
            />
          </div>
        </div>
        <div className="form-group row">
          <div className="col-sm-2">Active Status</div>
          <div className="col-sm-10">
            <div className="form-check">
              <input
                className="form-check-input"
                type="checkbox"
                id="activeStatus"
                name="activeStatus"
                checked={userData.activeStatus}
                onChange={handleCheckboxChange}
              />
              <label className="form-check-label" htmlFor="activeStatus">
                Active
              </label>
            </div>
          </div>
        </div>
        <div className="form-group row">
          <label htmlFor="roleNames" className="col-sm-2 col-form-label">Roles</label>
          <div className="col-sm-10">
            <select
              className="form-control"
              id="roleNames"
              name="roleNames"
              multiple
              value={userData.roleNames}
              onChange={handleMultiSelectChange}
            >
              {rolesList.map(role => (
                <option key={role} value={role}>{role}</option>
              ))}
            </select>
          </div>
        </div>
        <div className="form-group row">
          <label htmlFor="regionNames" className="col-sm-2 col-form-label">Regions</label>
          <div className="col-sm-10">
            <select
              className="form-control"
              id="regionNames"
              name="regionNames"
              multiple
              value={userData.regionNames}
              onChange={handleMultiSelectChange}
            >
              {regionsList.map(region => (
                <option key={region} value={region}>{region}</option>
              ))}
            </select>
          </div>
        </div>
        <div className="form-group row">
          <div className="col-sm-10 offset-sm-2">
            <button type="submit" className="btn btn-primary">Save Changes</button>
            <button type="button" className="btn btn-secondary ml-2" onClick={() => navigate('/users')}>Cancel</button>
          </div>
        </div>
      </form>
    </div>
  );
};

export default EditUserPage;
