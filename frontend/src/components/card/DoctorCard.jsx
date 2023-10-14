import React from 'react'
import { Link } from 'react-router-dom';

export default function DoctorCard({ name, specialty }) {
    return (
        <div className="max-w-md  bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
            <div classNameName='flex items-center justify-center'>
                <Link to="#">
                    <img className="rounded-lg" src="https://source.unsplash.com/200x150/?portrait" alt="Doctor Image" />
                </Link>
            </div>
            <div className="p-3 flex flex-col items-center justify-center">
                <Link to="#" >
                    <h5 className="mb-2 line-clamp-2 text-xl font-bold tracking-tight text-gray-900 dark:text-white">{name}</h5>
                </Link>
                <p className="mb-3 font-normal text-gray-700 dark:text-gray-400">{specialty}</p>
                <Link to="#" className="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-500 rounded-lg hover:bg-blue-600 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                    Book Appointment
                    <svg className="w-3.5 h-3.5 ml-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9" />
                    </svg>
                </Link>
            </div>
        </div>
    );
}
