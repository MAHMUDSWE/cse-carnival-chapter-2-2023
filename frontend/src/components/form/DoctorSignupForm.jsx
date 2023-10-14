import React, { useState } from 'react';
import ShowPassword from '../shared/ShowPassword';
import ErrorShow from '../shared/ErrorShow';
import validateInputs from '../../utils/formValidation.util';

export default function DoctorSignupForm({ onSubmit, signupError, setSignupError }) {
    const [inputs, setInputs] = useState({
        title: '',
        name: '',
        bmdc: '',
        dob: '',
        gender: '',
        district: '',
        thana: '',
        nid: '',
        doctorType: '',
        speciality: '',
        email: '',
        phone: '',
        username: '',
        password: '',
    });
    const [showPassword, setShowPassword] = useState(false);

    const handleSubmit = (event) => {
        event.preventDefault();

        const validationError = validateInputs(inputs);
        if (validationError) {
            setSignupError(validationError);
        } else {
            console.log(inputs);
            // onSubmit(inputs);
        }
    };

    const onTogglePassword = (showPassword) => {
        setShowPassword(showPassword);
    };

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setInputs((values) => ({ ...values, [name]: value }));
    };

    return (
        <>
            <form onSubmit={handleSubmit}>
                <div className="mb-2">
                    <label htmlFor="title" className="block font-medium mb-1">
                        Title
                    </label>
                    <select
                        type="text"
                        id="title"
                        name="title"
                        value={inputs.title || ''}
                        onChange={handleChange}
                        placeholder="Enter your title"
                        required
                        className={`w-full px-3 py-2 border rounded focus:outline-none ${signupError ? 'border-red-500' : 'border-gray-300 focus:border-blue-500'
                            }`} >
                        <option value="">Select your title</option>
                        <option value="Dr.">Dr.</option>
                        <option value="Prof. Dr.">Prof. Dr.</option>
                        <option value="Assoc. Prof. Dr.">Assoc. Prof. Dr.</option>
                        <option value="Asst. Prof. Dr.">Asst. Prof. Dr.</option>
                    </select>
                </div>


                <div className="mb-2">
                    <label htmlFor="name" className="block font-medium mb-1">
                        Name
                    </label>
                    <input
                        type="text"
                        id="name"
                        name="name"
                        value={inputs.name || ''}
                        onChange={handleChange}
                        placeholder="Enter your name"
                        required
                        className={`w-full px-3 py-2 border rounded focus:outline-none ${signupError ? 'border-red-500' : 'border-gray-300 focus:border-blue-500'
                            }`}
                    />
                </div>
                <div className="mb-2">
                    <label htmlFor="bmdc" className="block font-medium mb-1">
                        BMDC
                    </label>
                    <input
                        type="text"
                        id="bmdc"
                        name="bmdc"
                        value={inputs.bmdc || ''}
                        onChange={handleChange}
                        placeholder="Enter your BMDC registration number"
                        required
                        className={`w-full px-3 py-2 border rounded focus:outline-none ${signupError ? 'border-red-500' : 'border-gray-300 focus:border-blue-500'
                            }`}
                    />
                </div>
                <div className="mb-2 flex flex-col sm:flex-row">
                    <div className='sm:mr-4 w-1/2'>
                        <label htmlFor="doctorType" className="block font-medium mb-1 ">
                            Doctor Type
                        </label>
                        <select
                            type="text"
                            id="doctorType"
                            name="doctorType"
                            value={inputs.doctorType || ''}
                            onChange={handleChange}
                            placeholder="Enter your doctor type"
                            required
                            className={`w-full px-3 py-2 border rounded focus:outline-none ${signupError ? 'border-red-500' : 'border-gray-300 focus:border-blue-500'
                                }`}
                        >
                            <option value="">Select Doctor type</option>
                            <option value="medical">Medical</option>
                            <option value="dental">Dental</option>
                            <option value="veterinary">Veterinary</option>
                        </select>
                    </div>

                    <div className='w-1/2'>
                        <label htmlFor="speciality" className="block font-medium mb-1">
                            Speciality
                        </label>
                        <input
                            type="text"
                            id="speciality"
                            name="speciality"
                            value={inputs.speciality || ''}
                            onChange={handleChange}
                            placeholder="Enter your speciality"
                            required
                            className={`w-full px-3 py-2 border rounded focus:outline-none ${signupError ? 'border-red-500' : 'border-gray-300 focus:border-blue-500'
                                }`}
                        />
                    </div>
                </div>
                <div className="mb-2 flex flex-col sm:flex-row">
                    <div className='mr-4 w-1/2'>
                        <label htmlFor="dob" className="block font-medium mb-1">
                            Date of Birth
                        </label>
                        <input
                            type="date"
                            id="dob"
                            name="dob"
                            value={inputs.dob || ''}
                            onChange={handleChange}
                            required
                            className={`w-full px-3 py-2 border rounded focus:outline-none ${signupError ? 'border-red-500' : 'border-gray-300 focus:border-blue-500'
                                }`}
                        />
                    </div>
                    <div className=' w-1/2 '>
                        <label htmlFor="gender" className="block font-medium mb-1">
                            Gender
                        </label>
                        <select
                            type="text"
                            id="gender"
                            name="gender"
                            value={inputs.gender || ''}
                            onChange={handleChange}
                            placeholder="Enter your gender"
                            required
                            className={`w-full px-3 py-2 border rounded focus:outline-none ${signupError ? 'border-red-500' : 'border-gray-300 focus:border-blue-500'
                                }`}
                        >
                            <option value="">Select Gender</option>
                            <option value="male">Male</option>
                            <option value='female'>Female</option>
                        </select>
                    </div>
                </div>

                <div className="mb-2 flex flex-col sm:flex-row">
                    <div className='mr-4 w-1/2'>
                        <label htmlFor="district" className="block font-medium mb-1">
                            District
                        </label>
                        <input
                            type="text"
                            id="district"
                            name="district"
                            value={inputs.district || ''}
                            onChange={handleChange}
                            placeholder="Enter your district"
                            required
                            className={`w-full px-3 py-2 border rounded focus:outline-none ${signupError ? 'border-red-500' : 'border-gray-300 focus:border-blue-500'
                                }`}
                        />
                    </div>
                    <div className='w-1/2'>
                        <label htmlFor="thana" className="block font-medium mb-1">
                            Thana
                        </label>
                        <input
                            type="text"
                            id="thana"
                            name="thana"
                            value={inputs.thana || ''}
                            onChange={handleChange}
                            placeholder="Enter your thana"
                            required
                            className={`w-full px-3 py-2 border rounded focus:outline-none ${signupError ? 'border-red-500' : 'border-gray-300 focus:border-blue-500'
                                }`}
                        />
                    </div>
                </div>
                <div className="mb-2">
                    <label htmlFor="nid" className="block font-medium mb-1">
                        NID
                    </label>
                    <input
                        type="text"
                        id="nid"
                        name="nid"
                        value={inputs.nid || ''}
                        onChange={handleChange}
                        placeholder="Enter your NID"
                        required
                        className={`w-full px-3 py-2 border rounded focus:outline-none ${signupError ? 'border-red-500' : 'border-gray-300 focus:border-blue-500'
                            }`}
                    />
                </div>

                <div className="mb-2">
                    <label htmlFor="email" className="block font-medium mb-1">
                        Email
                    </label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={inputs.email || ''}
                        onChange={handleChange}
                        placeholder="Enter your email"
                        required
                        className={`w-full px-3 py-2 border rounded focus:outline-none ${signupError ? 'border-red-500' : 'border-gray-300 focus:border-blue-500'
                            }`}
                    />
                </div>
                <div className="mb-2">
                    <label htmlFor="phone" className="block font-medium mb-1">
                        Phone
                    </label>
                    <input
                        type="tel"
                        id="phone"
                        name="phone"
                        value={inputs.phone || ''}
                        onChange={handleChange}
                        placeholder="Enter your phone number"
                        required
                        className={`w-full px-3 py-2 border rounded focus:outline-none ${signupError ? 'border-red-500' : 'border-gray-300 focus:border-blue-500'
                            }`}
                    />
                </div>
                <div className="mb-2">
                    <label htmlFor="username" className="block font-medium mb-1">
                        Username
                    </label>
                    <input
                        type="text"
                        id="username"
                        name="username"
                        value={inputs.username || ''}
                        onChange={handleChange}
                        placeholder="Enter your username"
                        required
                        pattern="[a-zA-Z0-9]+"
                        className={`w-full px-3 py-2 border rounded focus:outline-none ${signupError ? 'border-red-500' : 'border-gray-300 focus:border-blue-500'
                            }`}
                    />
                </div>
                <div className="mb-6 relative">
                    <label htmlFor="password" className="block font-medium mb-1">
                        Password
                    </label>
                    <input
                        type={showPassword ? 'text' : 'password'}
                        id="password"
                        name="password"
                        value={inputs.password || ''}
                        onChange={handleChange}
                        required
                        className={`w-full px-3 py-2 border rounded focus:outline-none ${signupError ? 'border-red-500' : 'border-gray-300 focus:border-blue-500'
                            }`}
                        placeholder="Enter your password"
                    />
                    {inputs.password && <ShowPassword onTogglePassword={onTogglePassword} />}
                </div>
                <ErrorShow error={signupError} />
                <button
                    type="submit"
                    className="w-full py-2 px-4 bg-green-500 text-white font-semibold rounded hover:bg-green-600"
                >
                    Sign Up
                </button>
            </form>
        </>
    );
}



