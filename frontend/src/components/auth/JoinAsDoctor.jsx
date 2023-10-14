import React from 'react'
import { Link } from 'react-router-dom';

export default function JoinAsDoctor() {
    return (
        <div className="flex flex-row border-gray-300 mt-3 text-center">
            <div>
                <h1 className="text-xl font-semibold text-gray-800 mb-6 text-center" >
                    Are you a doctor? 
                </h1>
            </div>
            <div className='mt-1'>
                <Link
                    to="/doctor-registration"
                    className="border-2 rounded-lg ml-2 border-green-500 py-1 px-4  text-black font-semibold hover:bg-green-600 hover:text-white"
                >
                    Join As Doctor
                </Link>
            </div>
        </div>
    )
}
