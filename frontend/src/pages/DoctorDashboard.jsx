import React, { useState } from 'react';
import Navbar from '../components/nav/navbar';
import AppointmentCard from '../components/card/AppointmentCard';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGreaterThan, faLessThan } from '@fortawesome/free-solid-svg-icons';

const scheduledAppointmentsData = [
    {
        id: 1,
        patient: 'John Doe',
        specialty: 'Cardiology',
        date: '2023-10-15',
        time: '10:00 AM',
    },
    {
        id: 2,
        patient: 'Jane Smith',
        specialty: 'Orthopedics',
        date: '2023-10-16',
        time: '02:30 PM',
    },
    // Add more scheduled appointments
];

const pastAppointmentsData = [
    {
        id: 3,
        patient: 'Mary Johnson',
        specialty: 'Gynecology',
        date: '2023-10-10',
        time: '09:00 AM',
    },
    {
        id: 4,
        patient: 'James Brown',
        specialty: 'Dermatology',
        date: '2023-10-12',
        time: '03:15 PM',
    },
    // Add more past appointments
];

const DoctorInformation = () => {
    // Mock doctor profile data
    const doctorData = {
        name: "Dr. John Doe",
        specialty: "Cardiologist",
        email: "johndoe@example.com",
        isVerified: true, // Set to true for verified, false for unverified
        profileImage: "https://source.unsplash.com/200x200/?portrait" || "https://example.com/doctor-profile-image.jpg", // Replace with actual image URL
    };

    return (
        <>
            <div className="w-1/2 mx-auto p-4">
                {/* <h1 className="text-3xl font-semibold mb-4">Doctor Information</h1> */}
                <div className="text-center">
                    <img
                        src={doctorData.profileImage}
                        alt="Doctor Profile"
                        className="w-32 h-32 mx-auto rounded-full mb-4"
                    />
                    <h2 className="text-xl font-semibold mb-2">{doctorData.name}  {doctorData.isVerified ? (

                        <span className="bg-green-400 text-sm text-white px-1 py-1 rounded-md">
                            Verified
                        </span>

                    ) : (

                        <span className="bg-red-400 text-sm text-white px-2 py-1 rounded-full">
                            Unverified
                        </span>

                    )}</h2>
                    <p className="text-gray-600 mb-2">{doctorData.specialty}</p>
                    <p className="text-gray-600 mb-4">{doctorData.email}</p>

                </div>
            </div>
        </>
    );
};


const DoctorDashboard = () => {
    const [isScheduledAppointmentsOpen, setIsScheduledAppointmentsOpen] = useState(false);
    const [isPastAppointmentsOpen, setIsPastAppointmentsOpen] = useState(false);

    const toggleScheduledAppointments = () => {
        setIsScheduledAppointmentsOpen(!isScheduledAppointmentsOpen);
    };

    const togglePastAppointments = () => {
        setIsPastAppointmentsOpen(!isPastAppointmentsOpen);
    };

    return (
        <>
            <Navbar />
            <DoctorInformation />
            <div className="w-1/2 mx-auto p-4">
                <h1 className="text-3xl font-semibold mb-4">Doctor Dashboard</h1>

                <section className="mb-8">
                    <button
                        className="text-xl px-2 py-1 rounded-md bg-blue-500 hover:bg-blue-600 text-white font-semibold mb-4 cursor-pointer"
                        onClick={toggleScheduledAppointments}
                    >
                        Scheduled Appointments <FontAwesomeIcon
                            icon={isScheduledAppointmentsOpen ? faLessThan : faGreaterThan}
                            rotation={90}
                            size='sm'
                        />
                    </button>
                    {isScheduledAppointmentsOpen ? (
                        <ul className="space-y-4">
                            {scheduledAppointmentsData.map((appointment) => (
                                <AppointmentCard key={appointment.id} {...appointment} />
                            ))}
                        </ul>
                    ) : (
                        <p></p>
                    )}
                </section>

                <section>
                    <button
                        className="text-xl px-2 py-1 rounded-md bg-blue-500 hover.bg-blue-600 text-white font-semibold mb-4 cursor-pointer"
                        onClick={togglePastAppointments}
                    >
                        Past Appointments <FontAwesomeIcon
                            icon={isPastAppointmentsOpen ? faLessThan : faGreaterThan}
                            rotation={90}
                            size='sm'
                        />
                    </button>
                    {isPastAppointmentsOpen ? (
                        <ul className="space-y-4">
                            {pastAppointmentsData.map((appointment) => (
                                <AppointmentCard key={appointment.id} {...appointment} isPast={true} />
                            ))}
                        </ul>
                    ) : (
                        <p></p>
                    )}
                </section>
            </div>
        </>
    );
};

export { DoctorDashboard };