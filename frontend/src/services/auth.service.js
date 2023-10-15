import axiosInstance, { axiosInstance2 } from '../utils/axios.util';
import apiEndpoint from '../utils/endpoint.util';
import { removeAccessToken } from '../utils/token.util';
import axios from 'axios'

const signup = async (userData) => {
    console.log(userData);

    const response = await axiosInstance2.post(apiEndpoint.auth.signup, userData);
    const data = response.data;

    return data;
};


const doctorSignup = async (userData) => {
    console.log(userData);

    const doctorData = {
        Title: userData.title,
        firstName: userData.name,
        lastName: `Rhx${userData.name}`,
        username: userData.username,
        dob: userData.dob,
        gender: userData.gender,
        nationId: userData.nid,
        bmdc: userData.bmdc,
        doctorType: {
            name: userData.doctorType,
        },
        phoneNumber: userData.phone,
        email: userData.email,
        password: userData.password,
        role: {
            name: "DOCTOR",
        },
        isApproved: true,
    };

    console.log(doctorData);


    const response = await axiosInstance2.post(apiEndpoint.auth.doctorSignup, doctorData);
    const data = response.data;

    return data;
};

const login = async (credentials) => {
    const response = await axiosInstance2.post(apiEndpoint.auth.login, credentials);
    const data = response.data;

    return data;
};

const logout = async () => {
    try {
        removeAccessToken();

    } catch (error) {
        console.error('Error logging out:', error);
    }
};

export const AuthService = {
    signup, doctorSignup, login, logout
}
