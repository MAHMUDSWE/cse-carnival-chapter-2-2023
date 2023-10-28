import React, { useState, useEffect } from 'react';
import Navbar from '../components/nav/navbar';

const AdminDashboard = () => {
    const [doctors, setDoctors] = useState([
        {
            id: 1,
            name: 'Dr. John Doe',
            specialty: 'Cardiologist',
            bmdcNumber: 'BMDC12345',
            nidNumber: 'NID67890',
            verificationStatus: 'Pending',
        },
        {
            id: 2,
            name: 'Dr. John',
            specialty: 'Cardiologist',
            bmdcNumber: 'BMDC12345',
            nidNumber: 'NID67890',
            verificationStatus: 'Verified',
        },
        {
            id: 3,
            name: 'Dr. Helal',
            specialty: 'Cardiologist',
            bmdcNumber: 'BMDC12345',
            nidNumber: 'NID67890',
            verificationStatus: 'Verified',
        },
        {
            id: 4,
            name: 'Dr. Jane Smith',
            specialty: 'Orthopedic',
            bmdcNumber: 'BMDC67890',
            nidNumber: 'NID12345',
            verificationStatus: 'NotVerified',
        },
        {
            id: 5,
            name: 'Dr. Jane Smith',
            specialty: 'Orthopedic',
            bmdcNumber: 'BMDC67890',
            nidNumber: 'NID12345',
            verificationStatus: 'Verified',
        },
        {
            id: 6,
            name: 'Dr. Jane Smith',
            specialty: 'Orthopedic',
            bmdcNumber: 'BMDC67890',
            nidNumber: 'NID12345',
            verificationStatus: 'Pending',
        },
        {
            id: 7,
            name: 'Dr. Jane Smith',
            specialty: 'Orthopedic',
            bmdcNumber: 'BMDC67890',
            nidNumber: 'NID12345',
            verificationStatus: 'Pending',
        },
    ]);

    const [verificationStatus, setVerificationStatus] = useState('Pending');

    const fetchDoctorData = async () => {

    };

    const verifyDoctor = (doctorId) => {

    };

    const approveDoctor = (doctorId, approvalStatus) => {

    };

    useEffect(() => {
        fetchDoctorData();
    }, []);

    return (
        <div>
            <Navbar />
            <div>
                <h1 className="text-3xl text-center mt-5 font-semibold">Admin Dashboard</h1>
                <div className="my-4">
                    <label className="ml-4 mr-2">Verification Status:</label>
                    <select
                        className="px-2 py-1 rounded-md bg-gray-200"
                        onChange={(e) => setVerificationStatus(e.target.value)}
                    >
                        <option value="Pending">Pending</option>
                        <option value="Verified">Verified</option>
                        <option value="NotVerified">Not Verified</option>
                    </select>
                </div>
                <table className="w-full table-auto">
                    <thead>
                        <tr>
                            <th className="px-4 py-2">Name</th>
                            <th className="px-4 py-2">Specialty</th>
                            <th className="px-4 py-2">BMDC Number</th>
                            <th className="px-4 py-2">NID Number</th>
                            <th className="px-4 py-2">Verification Status</th>
                            <th className="px-4 py-2">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {doctors
                            .filter((doctor) => verificationStatus === doctor.verificationStatus)
                            .map((doctor) => (
                                <tr key={doctor.id}>
                                    <td className="px-4 py-2">{doctor.name}</td>
                                    <td className="px-4 py-2">{doctor.specialty}</td>
                                    <td className="px-4 py-2">{doctor.bmdcNumber}</td>
                                    <td className="px-4 py-2">{doctor.nidNumber}</td>
                                    <td className="px-4 py-2">{doctor.verificationStatus}</td>
                                    <td className="px-4 py-2">
                                        <button
                                            className="px-2 py-1 bg-green-500 text-white rounded hover:bg-green-600"
                                            onClick={() => verifyDoctor(doctor.id)}
                                        >
                                            Verify
                                        </button>
                                        <button
                                            className="px-2 py-1 bg-blue-500 text-white rounded hover:bg-blue-600 ml-2"
                                            onClick={() => approveDoctor(doctor.id, 'Approved')}
                                        >
                                            Approve
                                        </button>
                                        <button
                                            className="px-2 py-1 bg-red-500 text-white rounded hover:bg-red-600 ml-2"
                                            onClick={() => approveDoctor(doctor.id, 'Disapproved')}
                                        >
                                            Disapprove
                                        </button>
                                    </td>
                                </tr>
                            ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default AdminDashboard;
