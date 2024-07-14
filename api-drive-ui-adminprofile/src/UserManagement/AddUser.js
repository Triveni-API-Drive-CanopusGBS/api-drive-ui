import React, { useState, useEffect } from 'react';
import { uploadExcelSheet, submitUserDataRawJson, fetchDepartments, fetchRoles, fetchRegions, submitUserDataMultipart, handleUploadImage } from './userProfileService'; // adjust the import path as necessary
import  './usermgmt.css'
const AddUser = () => {
  const [formData, setFormData] = useState({
    employeeId: '',
    employeeName: '',
    emailId: '',
    contactNumber: '',
    groupId: '',
    modifiedBy: '',
    department: '',
    designation: '',
    activeStatus: '',
    roles: [],
    regions: [],
    image: null,
  });

  const [departmentList, setDepartmentList] = useState([]);
  const [rolesList, setRolesList] = useState([]);
  const [regionsList, setRegionsList] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const departmentsData = await fetchDepartments();
        setDepartmentList(departmentsData);

        const rolesData = await fetchRoles();
        setRolesList(rolesData);

        const regionsData = await fetchRegions();
        setRegionsList(regionsData);

        setLoading(false);
      } catch (error) {
        console.error('Error fetching data:', error);
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  const handleChange = e => {
    const { name, value, type, checked, files } = e.target;
    if (type === 'checkbox') {
      if (name === 'selectAllRoles') {
        setFormData({ ...formData, roles: checked ? rolesList : [] });
      } else if (name === 'selectAllRegions') {
        setFormData({ ...formData, regions: checked ? regionsList : [] });
      } else {
        const updatedArray = checked ? [...formData[name], value] : formData[name].filter(item => item !== value);
        setFormData({ ...formData, [name]: updatedArray });
      }
    } else if (type === 'file') {
      setFormData({ ...formData, image: files[0] });
    } else if (type === 'checkbox') {
      setFormData({ ...formData, [name]: checked });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const handleSubmit = e => {
    e.preventDefault();
    submitUserDataMultipart(formData)
      .then(response => {
        console.log('User data submitted successfully', response.data);
        resetForm();
      })
      .catch(error => {
        console.error('There was an error submitting the user data!', error);
      });
  };

  const handleExcelSubmit = e => {
    e.preventDefault();
    if (formData.image) {
      uploadExcelSheet(formData.image)
        .then(response => {
          console.log('Excel sheet uploaded successfully', response.data);
          resetForm();
        })
        .catch(error => {
          console.error('There was an error uploading the Excel sheet!', error);
        });
    } else {
      console.error('No file selected for upload');
    }
  };

  const resetForm = () => {
    setFormData({
      employeeId: '',
      employeeName: '',
      emailId: '',
      contactNumber: '',
      groupId: '',
      modifiedBy: '',
      department: '',
      designation: '',
      activeStatus: false,
      roles: [],
      regions: [],
      image: null,
    });
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <div className="container mt-5">
      <h2>Upload Multiple Users From Excel</h2>
      <div className="mb-3">
        <label htmlFor="image" className="form-label">
          Upload ExcelSheet
        </label>
        <input className="form-control" type="file" id="excel" name="image" onChange={handleChange} />
        <br />
        <button type="button" className="btn btn-success float-right" onClick={handleExcelSubmit}>
          Submit
        </button>
      </div>
      <h2>Single User Creation Using Form</h2>
      <form onSubmit={handleUploadImage}>
        <div className="mb-3">
          <label htmlFor="employeeId" className="form-label">
            Employee Id
          </label>
          <input type="text" className="form-control" id="employeeId" name="employeeId" value={formData.employeeId} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label htmlFor="employeeName" className="form-label">
            Employee Name
          </label>
          <input type="text" className="form-control" id="employeeName" name="employeeName" value={formData.employeeName} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label htmlFor="emailId" className="form-label">
            Email Id
          </label>
          <input type="email" className="form-control" id="emailId" name="emailId" value={formData.emailId} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label htmlFor="contactNumber" className="form-label">
            Contact Number
          </label>
          <input type="text" className="form-control" id="contactNumber" name="contactNumber" value={formData.contactNumber} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label htmlFor="groupId" className="form-label">
            Group Id
          </label>
          <input type="text" className="form-control" id="groupId" name="groupId" value={formData.groupId} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label htmlFor="modifiedBy">Modified By:</label>
          <input type="text" className="form-control" id="modifiedBy" name="modifiedBy" value={formData.modifiedBy} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label htmlFor="department" className="form-label">
            Department
          </label>
          <select className="form-select" id="department" name="department" value={formData.department} onChange={handleChange} required>
            <option value="" disabled>
              Please select
            </option>
            {departmentList.map((dept, index) => (
              <option key={index} value={dept}>
                {dept}
              </option>
            ))}
          </select>
        </div>
        <div className="mb-3">
          <label htmlFor="designation" className="form-label">
            Designation
          </label>
          <input type="text" className="form-control" id="designation" name="designation" value={formData.designation} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Select Roles</label>
          <div className="form-check">
            <input className="form-check-input" type="checkbox" id="selectAllRoles" name="selectAllRoles" onChange={handleChange} checked={formData.roles.length === rolesList.length} />
            <label className="form-check-label" htmlFor="selectAllRoles">
              Select All
            </label>
          </div>
          {rolesList.map((role, index) => (
            <div className="form-check" key={index}>
              <input className="form-check-input" type="checkbox" id={`role${index}`} name="roles" value={role} onChange={handleChange} checked={formData.roles.includes(role)} />
              <label className="form-check-label" htmlFor={`role${index}`}>
                {role}
              </label>
            </div>
          ))}
        </div>
        <div className="mb-3">
          <label className="form-label">Select Regions</label>
          <div className="form-check">
            <input className="form-check-input" type="checkbox" id="selectAllRegions" name="selectAllRegions" onChange={handleChange} checked={formData.regions.length === regionsList.length} />
            <label className="form-check-label" htmlFor="selectAllRegions">
              Select All
            </label>
          </div>
          {regionsList.map((region, index) => (
            <div className="form-check" key={index}>
              <input className="form-check-input" type="checkbox" id={`region${index}`} name="regions" value={region} onChange={handleChange} checked={formData.regions.includes(region)} />
              <label className="form-check-label" htmlFor={`region${index}`}>
                {region}
              </label>
            </div>
          ))}
        </div>
        <div className="mb-3 form-check">
          <input className="form-check-input" type="checkbox" id="activeStatus" name="activeStatus" checked={formData.activeStatus} onChange={handleChange} />
          <label className="form-check-label" htmlFor="activeStatus">
            Active
          </label>
          
        </div>
        <div className="mb-3">
          <label htmlFor="image" className="form-label">
            Upload Image
          </label>
          <input className="form-control" type="file" id="image" name="image" value={formData.image} onChange={handleChange} />
        </div>

        <button type="submit" className="btn btn-success">
          Submit
        </button>
        <button type="reset" className="btn btn-info" onClick={resetForm}>
          Reset
        </button>
        <button type="button" className="btn btn-danger" onClick={() => console.log('Cancel')}>
          Cancel
        </button>
      </form>
    </div>
  );
};

export default AddUser;
