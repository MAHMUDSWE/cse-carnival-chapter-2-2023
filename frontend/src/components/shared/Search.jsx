import React, { useState, useRef, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBlog, faSearch, faTimes } from '@fortawesome/free-solid-svg-icons';
import { useQuery } from '@tanstack/react-query';

import { Link } from 'react-router-dom';
import UserService from '../../services/user.service';

export default function SearchBar() {
    const [searchText, setSearchText] = useState('');
    const [isFocused, setIsFocused] = useState(false);
    const inputRef = useRef(null);
    const [searchResults, setSearchResult] = useState([]);

    const { data: user } = useQuery({
        enabled: !!searchText,
        queryKey: ['skipLoading', 'getSearchResults', searchText],
        queryFn: async () => await UserService.getSearchResult(searchText),
        staleTime: 15000
    });

    useEffect(() => {
        if (user) {
            setSearchResult(user);
        }
    }, [user]);

    const handleInputChange = (e) => {
        setSearchText(e.target.value);
    };

    const handleClearSearch = () => {
        setSearchText('');
        inputRef.current.focus();
    };

    return (
        <div className="relative container max-w-4xl mx-auto px-4 py-4">
            <div
                className={`flex items-center rounded-full gap-2 px-6 py-3 transition duration-300 ${isFocused
                    ? 'bg-white border border-blue-400 shadow'
                    : 'bg-[#EFF3F4] border border-[#EFF3F4]'
                    }`}
            >
                <FontAwesomeIcon
                    icon={faSearch}
                    className={`mr-2 ${isFocused ? 'text-blue-400' : 'text-gray-500'}`}
                />

                <input
                    type="text"
                    placeholder="Search Healthcare Expert"
                    value={searchText}
                    onChange={handleInputChange}
                    onFocus={() => setIsFocused(true)}
                    onBlur={() => setIsFocused(false)}
                    ref={inputRef}
                    className="flex-grow outline-none bg-transparent"
                />
                {searchText && (
                    <button
                        data-testid="clearSearch"
                        onClick={handleClearSearch}
                        className="w-6 h-6 bg-blue-500 rounded-full flex items-center justify-center"
                    >
                        <FontAwesomeIcon
                            icon={faTimes}
                            className="text-white cursor-pointer"
                        />
                    </button>
                )}
            </div>

            {searchText.length > 0 && (
                <div className="absolute mx-auto mt-1 inset-x-0 w-[92%] sm:w-[95%] max-h-40 lg:max-h-96 overflow-y-auto z-20 drop-shadow-sm shadow-[0px_3px_0px_0px] shadow-blue-500 rounded-md bg-white">
                    <ul>
                        {searchResults.map((doctor) => (
                            <li key={doctor.userid} className="gap-1 border-b-2 border-blue-200">
                                <Link to={`/doctor/appointment/${doctor.userid}}`} className="text-blue-500">
                                    <div className="flex py-2 pl-6 pr-8 hover:bg-indigo-50 justify-between items-center gap-2">
                                        <span className="line-clamp-2"><FontAwesomeIcon icon={faBlog} /> {doctor.speciality}</span>
                                        <span >{doctor.name}</span>
                                    </div>
                                </Link>
                            </li>
                        ))}
                    </ul>
                </div>
            )}

            {(searchText.length > 0 && searchResults.length === 0) && (
                <div
                    className="absolute flex-col mx-auto mt-1 inset-x-0  w-[92%] sm:w-[95%] z-20 p-7 shadow-[0px_3px_0px_0px] text-center shadow-blue-500 rounded-md bg-white">
                    <span><FontAwesomeIcon icon={faSearch} size='lg' /></span>
                    <span className='text-lg'> No Results Found </span>
                    <div>Try with specific keywords for better result</div>
                </div>
            )}

            {isFocused && searchText.length <= 0 && (
                <div
                    className="absolute mx-auto mt-1 inset-x-0  w-[92%] sm:w-[95%] z-20 p-8 shadow-[0px_3px_0px_0px] text-center shadow-blue-500 rounded-md bg-white">
                    <span className='text-lg'>Try searching for expert doctor</span>
                </div>
            )}
        </div>
    );
}
