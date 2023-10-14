import React from 'react';
import { Outlet } from 'react-router-dom';
import LoginPageSVG from "../assets/LoginPage.svg";


function IndexPage() {

    return (
        <div className="flex flex-col sm:flex-row bg-gray-100">
            <div className="opacity-90 flex flex-col bg-gray-100 h-screen sticky top-0  sm:w-1/2 CenterContent">
                <h1 className="text-4xl font-semibold text-primary mb-6 text-center" >
                    Welcome to <span className="">ReachOut</span></h1>
                <object
                    type="image/svg+xml"
                    data={LoginPageSVG}
                    className="max-h-[500px] w-auto h-auto"
                />
            </div>
            <div className="sm:w-1/2">
                <Outlet />
            </div>
        </div>
    );
}

export default IndexPage;