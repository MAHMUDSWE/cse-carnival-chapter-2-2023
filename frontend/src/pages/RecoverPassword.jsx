import React, { useState } from 'react';

const RecoverPassword = () => {
    const [email, setEmail] = useState('');
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const handleRecoverPassword = async (e) => {
        e.preventDefault();
    };

    return (
        <div className="mx-auto mt-60 max-w-md p-4 bg-white rounded shadow-lg">
            <h2 className="text-2xl font-semibold text-center">Recover Password</h2>
            {message && (
                <p className="mt-2 p-2 text-green-500 bg-green-100 rounded">
                    {message}
                </p>
            )}
            {error && (
                <p className="mt-2 p-2 text-red-500 bg-red-100 rounded">{error}</p>
            )}
            <form onSubmit={handleRecoverPassword} className="mt-4">
                <div className="mb-4">
                    <label htmlFor="email" className="block text-sm font-medium text-gray-700">
                        Email:
                    </label>
                    <input
                        type="email"
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                        className="mt-1 p-2 w-full border rounded-md focus:ring focus:ring-blue-400 focus:ring-opacity-50"
                    />
                </div>
                <button
                    type="submit"
                    className="w-full p-2 bg-blue-500 text-white rounded hover:bg-blue-600 focus:ring focus:ring-blue-400 focus:ring-opacity-50"
                >
                    Recover Password
                </button>
            </form>
        </div>
    );
};

export default RecoverPassword;
