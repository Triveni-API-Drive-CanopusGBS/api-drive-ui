// Container.js
import React from 'react';
import './Container.css'; // Assuming you have this CSS file for styling

const Container = ({ children }) => {
    return <div className="content-container">{children}</div>;
};

export default Container;
