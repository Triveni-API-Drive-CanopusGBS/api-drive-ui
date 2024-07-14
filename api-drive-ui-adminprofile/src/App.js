import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Header from './Home/Header';
import AddUser from './UserManagement/AddUser';
import EditUser from './UserManagement/EditUser';
import AllUsersInfo from './UserManagement/AllUsersInfo';
import ProfileData from './UserManagement/ProfileData';
import Footer from './Home/Footer';
import Home from './Home/Home';
import NotFound from './Home/NotFound';

const App = () => {
  return (
    <Router>
      <div className="App">
        <Header />
        <div className="container mt-3">
          <Routes>
                 {/* You can either Component or Element.   */}
            <Route exact path="/" Component={Home} />
            <Route path="/profile" Component={ProfileData} />
            <Route path="/adduser" element={<AddUser />} />
            <Route path="/edituser/:id" element={<EditUser />} />
            <Route path="/users" element={<AllUsersInfo />} />
            <Route path= "*" Component={NotFound} /> {/* Fallback for 404 Not Found */}
          </Routes>
          <Footer />
        </div>
      </div>
    </Router>
  );
};

export default App;
