import React, { useState } from 'react';
import Navbar from '../components/nav/navbar';
import AppointmentCard from '../components/card/AppointmentCard';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faGreaterThan, faLessThan } from '@fortawesome/free-solid-svg-icons';

const scheduledAppointmentsData = [
    {
        id: 1,
        doctor: 'Dr. John Doe',
        specialty: 'Cardiologist',
        date: '2023-10-15',
        time: '10:00 AM',
    },
    {
        id: 2,
        doctor: 'Dr. Jane Smith',
        specialty: 'Orthopedic',
        date: '2023-10-16',
        time: '02:30 PM',
    },
    // Add more scheduled appointments
];

const pastAppointmentsData = [
    {
        id: 3,
        doctor: 'Dr. Mary Johnson',
        specialty: 'Gynecologist',
        date: '2023-10-10',
        time: '09:00 AM',
    },
    {
        id: 4,
        doctor: 'Dr. James Brown',
        specialty: 'Dermatologist',
        date: '2023-10-12',
        time: '03:15 PM',
    },
    // Add more past appointments
];

const PatientDashboard = () => {
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
            <div className="w-1/2 mx-auto p-4">
                <h1 className="text-3xl font-semibold mb-4">Patient Dashboard</h1>

                <section className="mb-8">
                    <button
                        className="text-xl px-2 py-1 rounded-md bg-blue-500 hover:bg-blue-600 text-white font-semibold mb-4 cursor-pointer"
                        onClick={toggleScheduledAppointments}>
                        Current Scheduled Appointments <FontAwesomeIcon
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
                        </ul>)
                        : (
                            <p></p>
                        )}
                </section>

                <section>
                    <button
                        className="text-xl px-2 py-1 rounded-md bg-blue-500 hover:bg-blue-600 text-white font-semibold mb-4 cursor-pointer"
                        onClick={togglePastAppointments}>
                        History of Past Appointments <FontAwesomeIcon
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
                        </ul>)
                        : (
                            <p></p>
                        )}
                </section>
            </div>
        </>
    );
};



export default PatientDashboard;
