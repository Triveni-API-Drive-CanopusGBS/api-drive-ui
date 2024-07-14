import React from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

const Header = () => {
  return (
    <header className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/">Welcome to Triveni!!</Link>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav ms-auto">
            <li className="nav-item">
              <Link className="nav-link" to="/profile">Profile</Link>
            </li>
            <li className="nav-item dropdown">
              <a className="nav-link dropdown-toggle" href="/" id="userManagementDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                User Management
              </a>
              <ul className="dropdown-menu" aria-labelledby="userManagementDropdown">
                <li><Link className="dropdown-item" to="/adduser">Add User</Link></li>
                <li><Link className="dropdown-item" to="/edituser/id">Edit User</Link></li>
                <li><Link className="dropdown-item" to="/users">All User Info</Link></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </header>
  );
};

export default Header;
