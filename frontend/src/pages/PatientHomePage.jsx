import React, { useState } from 'react';
import DoctorCard from '../components/card/DoctorCard';
import Navbar from '../components/nav/navbar';
import SearchBar from '../components/shared/Search';

const specialties = ['Cardiologist', 'Orthopedic', 'Gynecologist']; // Sample specialties
const doctorsData = [
    { name: 'Dr. John Doe', specialty: 'Cardiologist', location: 'New York' },
    { name: 'Dr. John Doe', specialty: 'Cardiologist', location: 'New York' },
    { name: 'Dr. Jane Smith', specialty: 'Orthopedic', location: 'Los Angeles' },
    { name: 'Dr. Jane Smith', specialty: 'Orthopedic', location: 'Los Angeles' },
    { name: 'Dr. Mary Johnson', specialty: 'Gynecologist', location: 'Chicago' },
    { name: 'Dr. Mary Johnson', specialty: 'Gynecologist', location: 'Chicago' },
    { name: 'Dr. Mary Johnson', specialty: 'Gynecologist', location: 'Chicago' },
    { name: 'Dr. Mary Johnson', specialty: 'Gynecologist', location: 'Chicago' },
    { name: 'Dr. Mary Johnson', specialty: 'Gynecologist', location: 'Chicago' },
    { name: 'Dr. Mary Johnson', specialty: 'Gynecologist', location: 'Chicago' },
];

const PatientHomepage = () => {
    const [selectedSpecialty, setSelectedSpecialty] = useState('');

    return (
        <>
            <Navbar />
            <div className="flex flex-col sm:flex-row gap-4">
                <div className="w-1/4 p-4 bg-white rounded shadow">
                    <div className='sticky top-20'>
                        <h2 className="text-2xl font-semibold mb-4">Filters</h2>
                        {/* Include SpecialtyFilter component */}
                        <div className="mb-4">
                            <label htmlFor="specialty" className="block font-medium mb-1">Specialty</label>
                            <select
                                id="specialty"
                                value={selectedSpecialty}
                                onChange={(e) => setSelectedSpecialty(e.target.value)}
                                className="w-full px-3 py-2 border rounded focus:outline-none"
                            >
                                <option value="">All Specialties</option>
                                {specialties.map((specialty, index) => (
                                    <option key={index} value={specialty}>{specialty}</option>
                                ))}
                            </select>
                        </div>
                    </div>
                    {/* Add more filter components for district, thana, price range, etc. */}
                </div>
                <div className="max-w-screen-md w-2/4 p-4 bg-white rounded shadow overflow-y-auto">
                    <div>
                        <h1 className="text-3xl font-semibold mb-4">Find a Doctor</h1>
                        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
                            {doctorsData
                                .filter((doctor) => !selectedSpecialty || doctor.specialty === selectedSpecialty)
                                .map((doctor, index) => (
                                    <DoctorCard
                                        key={index}
                                        name={doctor.name}
                                        specialty={doctor.specialty}
                                        location={doctor.location}
                                    />
                                ))}
                        </div>
                    </div>
                </div>

                <div className="w-1/4 p-4">
                    <div className="mb-4 sticky top-20">
                        {/* <input
                            type="text"
                            placeholder="Search for a doctor..."
                            className="w-full px-3 py-2 border rounded focus:outline-none focus:border-blue-500"
                        /> */}
                        <SearchBar />
                    </div>
                </div>
            </div>
        </>
    );
};

export default PatientHomepage;
