import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Header from './Home/Header';
import AddUser from './UserManagement/AddUser';
import EditUser from './UserManagement/EditUser';
import AllUsersInfo from './UserManagement/AllUsersInfo';
import Profile from './UserManagement/Profile';
import Footer from './Home/Footer';

const App = () => {
  return (
    <Router>
      <div className="App">
        <Header />
        <div className="container mt-3">
          <Routes>
                 {/* You can either Componnet or Element.   */}
            <Route path="/profile" Component={Profile} />
            <Route path="/adduser" element={<AddUser />} />
            <Route path="/edituser" element={<EditUser />} />
            <Route path="/users" element={<AllUsersInfo />} />
          </Routes>
          <Footer />
        </div>
      </div>
    </Router>
  );
};

export default App;
