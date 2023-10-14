import React from 'react'
import { Navigate } from 'react-router-dom';
import { getAccessToken, getUserRole } from '../utils/token.util';

const LoggedOutMode = ({ children }) => {
    var isLoggedIn = !!getAccessToken();
    var role = getUserRole();
    if (isLoggedIn && role == "patient") {
        return <Navigate to="/patient-homepage" replace />;
    }
    else if (isLoggedIn && role == "doctor") {
        return <Navigate to="/doctor-dashboard" replace />;
    }
    else if (isLoggedIn && role == "admin") {
        return <Navigate to="/admin-dashboard" replace />;
    }
    return children;
}

const LoggedInMode = ({ children }) => {
    var isLoggedIn = !!getAccessToken();
    if (!isLoggedIn) {
        return <Navigate to="/" replace />;
    }
    return children;
}

const LoggedInModePatient = ({ children }) => {
    var isLoggedIn = !!getAccessToken();
    var role = getUserRole();
    if (!isLoggedIn && role == "patient") {
        return <Navigate to="/patient-dashboard" replace />;
    }
    return children;
}



export { LoggedOutMode, LoggedInMode };