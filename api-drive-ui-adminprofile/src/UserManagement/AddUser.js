import React, { useState } from 'react';
import { uploadExcelSheet, submitUserData } from './userService'; // adjust the import path as necessary

const AddUser = () => {
  const [formData, setFormData] = useState({
    employeeId: '',
    employeeName: '',
    emailId: '',
    contactNumber: '',
    modifiedBy: '',
    department: '',
    designation: '',
    roles: [],
    regions: [],
    image: null,
  });

  const rolesList = [
    'Admin', 'Cost Estimation Approver', 'Cost Estimation Engineer', 'Cost Estimation Reviewer', 
    'DBO Electrical Approver', 'DBO Electrical Engineer', 'DBO Electrical Reviewer', 'DBO Mechanical Approver', 
    'DBO Mechanical Engineer', 'DBO Mechanical Reviewer', 'Erection & Commission Approver', 'Erection & Commission Engineer', 
    'Erection & Commission Reviewer', 'F2F Approver', 'F2F Engineer', 'F2F Reviewer', 'Finance Approver', 'Finance Engineer', 
    'Packaging & Forwarding Approver', 'Packaging & Forwarding Engineer', 'Packaging & Forwarding Reviewer', 'Projects Admin', 
    'Projects Executive', 'Sub-contracting & PPE Approver', 'Sub-contracting & PPE Engineer', 'Sub-contracting & PPE Reviewer', 
    'Transportation Approver', 'Transportation Engineer', 'Transportation Reviewer', 'UBO Approver', 'UBO Engineer', 'UBO Reviewer'
  ];

  const regionsList = [
    'Africa (AFR)', 'Africa (WAFR)', 'Africa (EAFR)', 'Africa (SADC)', 'CSA (CSA)', 'East (EEQ)', 'Europe (EUR)', 
    'Europe (WUR)', 'MENA (MEA)', 'Middle East region (MEN)', 'North (NEQ)', 'North America (CNA)', 'ROW (ROW)', 
    'SAARC (ASA)', 'SAARC (BAN)', 'SAARC (PAK)', 'SEA2 (THA)', 'SEA2 (KOR)', 'SEA2 (SEA)', 'South (SEO)', 'Turkey (TUR)', 
    'West Pune (WPQ)', 'West Mumbai (WMQ)'
  ];

  const departmentList = [
    "DBO Electrical",
    "DBO Mechanical",
    "Engineering",
    "Finance",
    "Infotech",
    "Manufacturing",
    "Marketing",
    "Mktg_Sales",
    "Project_Sales",
    "Projects",
    "Proposals",
    "Sales",
    "Shipping",
    "Sub-contracting & PPE",
    "Transportation",
    "UBO"
  ];

  const handleChange = (e) => {
    const { name, value, type, checked, files } = e.target;
    if (type === 'checkbox') {
      let updatedArray = formData[name];
      if (checked) {
        updatedArray = [...updatedArray, value];
      } else {
        updatedArray = updatedArray.filter(item => item !== value);
      }
      setFormData({ ...formData, [name]: updatedArray });
    } else if (type === 'file') {
      setFormData({ ...formData, image: files[0] });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    submitUserData(formData)
      .then(response => {
        console.log('User data submitted successfully', response.data);
        resetForm();
      })
      .catch(error => {
        console.error('There was an error submitting the user data!', error);
      });
  };

  const handleExcelSubmit = (e) => {
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
      modifiedBy: '',
      department: '',
      designation: '',
      roles: [],
      regions: [],
      image: null,
    });
  };

  return (
    <div className="container mt-5">
      <h2>Upload Multiple Users From Excel</h2>
      <div className="mb-3">
        <label htmlFor="image" className="form-label">Upload ExcelSheet</label>
        <input className="form-control" type="file" id="excel" name="image" onChange={handleChange} /><br />
        <button type="button" className="btn btn-success float-right" onClick={handleExcelSubmit}>Submit</button>
      </div>
      <h2>Single User Creation Using Form</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="employeeId" className="form-label">Employee Id</label>
          <input type="text" className="form-control" id="employeeId" name="employeeId" value={formData.employeeId} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label htmlFor="employeeName" className="form-label">Employee Name</label>
          <input type="text" className="form-control" id="employeeName" name="employeeName" value={formData.employeeName} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label htmlFor="emailId" className="form-label">Email Id</label>
          <input type="email" className="form-control" id="emailId" name="emailId" value={formData.emailId} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label htmlFor="contactNumber" className="form-label">Contact Number</label>
          <input type="text" className="form-control" id="contactNumber" name="contactNumber" value={formData.contactNumber} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label htmlFor="modifiedBy">Modified By:</label>
          <input type="text" className="form-control" id="modifiedBy" name="modifiedBy" value={formData.modifiedBy}  onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label htmlFor="department" className="form-label">Department</label>
          <select className="form-select" id="department" name="department" value={formData.department} onChange={handleChange} required>
            <option value="" disabled>Please select</option>
            {departmentList.map((dept, index) => (
              <option key={index} value={dept}>{dept}</option>
            ))}
          </select>
        </div>
        <div className="mb-3">
          <label htmlFor="designation" className="form-label">Designation</label>
          <input type="text" className="form-control" id="designation" name="designation" value={formData.designation} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Role</label>
          <div className="form-check">
            <input className="form-check-input" type="checkbox" id="selectAllRoles" name="selectAllRoles" onChange={() => {
              if (formData.roles.length === rolesList.length) {
                setFormData({ ...formData, roles: [] });
              } else {
                setFormData({ ...formData, roles: rolesList });
              }
            }} checked={formData.roles.length === rolesList.length} />
            <label className="form-check-label" htmlFor="selectAllRoles">Select All</label>
          </div>
          {rolesList.map((role, index) => (
            <div className="form-check" key={index}>
              <input className="form-check-input" type="checkbox" id={`role${index}`} name="roles" value={role} onChange={handleChange} checked={formData.roles.includes(role)} />
              <label className="form-check-label" htmlFor={`role${index}`}>{role}</label>
            </div>
          ))}
        </div>
        <div className="mb-3">
          <label className="form-label">Region Coverage</label>
          <div className="form-check">
            <input className="form-check-input" type="checkbox" id="selectAllRegions" name="selectAllRegions" onChange={() => {
              if (formData.regions.length === regionsList.length) {
                setFormData({ ...formData, regions: [] });
              } else {
                setFormData({ ...formData, regions: regionsList });
              }
            }} checked={formData.regions.length === regionsList.length} />
            <label className="form-check-label" htmlFor="selectAllRegions">Select All</label>
          </div>
          {regionsList.map((region, index) => (
            <div className="form-check" key={index}>
              <input className="form-check-input" type="checkbox" id={`region${index}`} name="regions" value={region} onChange={handleChange} checked={formData.regions.includes(region)} />
              <label className="form-check-label" htmlFor={`region${index}`}>{region}</label>
            </div>
          ))}
        </div>
        <div className="mb-3">
          <label htmlFor="image" className="form-label">Upload Image</label>
          <input className="form-control" type="file" id="image" name="image" disabled onChange={handleChange} />
        </div>
        <button type="submit" className="btn btn-success">Submit</button>
        <button type="reset" className="btn btn-info" onClick={resetForm}>Reset</button>
        <button type="button" className="btn btn-danger" onClick={() => console.log('Cancel')}>Cancel</button>
      </form>
    </div>
  );
};

export default AddUser;
