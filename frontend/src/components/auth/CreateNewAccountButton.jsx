import React from 'react'
import { Link } from 'react-router-dom';

export default function CreateNewAccountButton() {
    return (
        <div className="border-t border-gray-300 mt-6 pt-6 text-center">
            <Link
                to="/patient-registration"
                className="w-full py-2 px-4 bg-green-500 text-white font-semibold rounded hover:bg-green-600"
            >
                Join As Patient
            </Link>
        </div>
    )
}
