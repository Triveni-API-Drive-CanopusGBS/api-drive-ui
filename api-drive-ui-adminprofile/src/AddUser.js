import React, { useState } from 'react';

const AddUser = () => {
  const [formData, setFormData] = useState({
    employeeId: '',
    employeeName: '',
    email: '',
    contactNumber: '',
    department: '',
    designation: '',
    roles: [],
    photo: null,
  });

  const rolesList = [
    'Admin', 'Cost Estimation Approver', 'Cost Estimation Engineer', 'Cost Estimation Reviewer', 'DBO Electrical Approver', 
    'DBO Electrical Engineer', 'DBO Electrical Reviewer', 'DBO Mechanical Approver', 'DBO Mechanical Engineer', 'DBO Mechanical Reviewer', 
    'Erection & Commission Approver', 'Erection & Commission Engineer', 'Erection & Commission Reviewer', 'F2F Approver', 
    'F2F Engineer', 'F2F Reviewer', 'Finance Approver', 'Finance Engineer', 'Packaging & Forwarding Approver', 'Packaging & Forwarding Engineer', 
    'Packaging & Forwarding Reviewer', 'Projects Admin', 'Projects Executive', 'Sub-contracting & PPE Approver', 'Sub-contracting & PPE Engineer', 
    'Sub-contracting & PPE Reviewer', 'Transportation Approver', 'Transportation Engineer', 'Transportation Reviewer', 'UBO Approver', 'UBO Engineer', 
    'UBO Reviewer'
  ];

  const handleChange = (e) => {
    const { name, value, type, checked, files } = e.target;
    if (type === 'checkbox') {
      let updatedRoles = formData.roles;
      if (checked) {
        updatedRoles = [...updatedRoles, value];
      } else {
        updatedRoles = updatedRoles.filter(role => role !== value);
      }
      setFormData({ ...formData, roles: updatedRoles });
    } else if (type === 'file') {
      setFormData({ ...formData, photo: files[0] });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Handle form submission logic here
    console.log(formData);
  };

  return (
    <div className="container mt-5">
      <h2>New User Creation</h2>
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
          <label htmlFor="email" className="form-label">Email Id</label>
          <input type="email" className="form-control" id="email" name="email" value={formData.email} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label htmlFor="contactNumber" className="form-label">Contact Number</label>
          <input type="text" className="form-control" id="contactNumber" name="contactNumber" value={formData.contactNumber} onChange={handleChange} />
        </div>
        <div className="mb-3">
          <label htmlFor="department" className="form-label">Department</label>
          <select className="form-select" id="department" name="department" value={formData.department} onChange={handleChange} required>
            <option value="" disabled>Please select</option>
            <option value="Department1">Department1</option>
            <option value="Department2">Department2</option>
            {/* Add more department options as needed */}
          </select>
        </div>
        <div className="mb-3">
          <label htmlFor="designation" className="form-label">Designation</label>
          <input type="text" className="form-control" id="designation" name="designation" value={formData.designation} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Role</label>
          <div className="form-check">
            <input className="form-check-input" type="checkbox" id="selectAll" name="selectAll" onChange={() => {
              if (formData.roles.length === rolesList.length) {
                setFormData({ ...formData, roles: [] });
              } else {
                setFormData({ ...formData, roles: rolesList });
              }
            }} checked={formData.roles.length === rolesList.length} />
            <label className="form-check-label" htmlFor="selectAll">Select All</label>
          </div>
          {rolesList.map((role, index) => (
            <div className="form-check" key={index}>
              <input className="form-check-input" type="checkbox" id={`role${index}`} name="roles" value={role} onChange={handleChange} checked={formData.roles.includes(role)} />
              <label className="form-check-label" htmlFor={`role${index}`}>{role}</label>
            </div>
          ))}
        </div>
        <div className="mb-3">
          <label htmlFor="photo" className="form-label">Upload Photo</label>
          <input className="form-control" type="file" id="photo" name="photo" onChange={handleChange} />
        </div>
        <button type="submit" className="btn btn-primary">Submit</button>
        <button type="reset" className="btn btn-secondary" onClick={() => setFormData({
          employeeId: '',
          employeeName: '',
          email: '',
          contactNumber: '',
          department: '',
          designation: '',
          roles: [],
          photo: null,
        })}>Reset</button>
        <button type="button" className="btn btn-danger" onClick={() => console.log('Cancel')}>Cancel</button>
      </form>
    </div>
  );
};

export default AddUser;
