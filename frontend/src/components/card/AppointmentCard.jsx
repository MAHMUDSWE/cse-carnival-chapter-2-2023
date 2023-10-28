import React from "react";
import JoinMeeting from "../shared/JoinMeeting";

const AppointmentCard = ({ doctor, specialty, date, time, isPast = false }) => {
    return (
        <div className="">
            <li className="p-4 bg-white rounded-lg shadow-md">
                <h3 className="text-lg font-semibold">{doctor}</h3>
                <p className="text-gray-600">{specialty}</p>
                <p>Date: {date}</p>
                <p>Time: {time}</p>
                {isPast && <p>Status: Past Appointment</p>}
                {!isPast && <JoinMeeting />}
            </li>
        </div>
    );
};

export default AppointmentCard;