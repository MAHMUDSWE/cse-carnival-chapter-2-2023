import { BrowserRouter, Routes, Route } from "react-router-dom"

import IndexPage from '../pages/IndexPage';
import LoginPage from '../pages/LoginPage';
import ErrorPage from '../pages/ErrorPage';
import PatientSignupPage from "../pages/PatientSignupPage";
import DoctorSignupPage from "../pages/DoctorSignupPage";
import { LoggedInMode, LoggedOutMode } from "./protectedRoutes";
import PatientHomepage from "../pages/PatientHomePage";
import PatientDashboard from "../pages/PatientDashboard";
import { DoctorDashboard } from "../pages/DoctorDashboard";
import RecoverPassword from "../pages/RecoverPassword";
import AdminDashboard from "../pages/AdminDashboard";


function PageRoutes() {

    return (
        <div>
            {/* <RouterProvider router={routes} /> */}
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<LoggedOutMode>
                        <IndexPage />
                    </LoggedOutMode>
                    } >
                        <Route path="/" element={<LoggedOutMode>
                            <LoginPage />
                        </LoggedOutMode>} />

                        <Route path="patient-registration" element={<LoggedOutMode>
                            <PatientSignupPage />
                        </LoggedOutMode>} />

                        <Route path="/doctor-registration" element={<LoggedOutMode>
                            <DoctorSignupPage />
                        </LoggedOutMode>} />
                    </Route>

                    <Route path="/login" element={<LoggedOutMode>
                        <LoginPage />
                    </LoggedOutMode>
                    } />

                    <Route path="/recover" element={<LoggedOutMode>
                        <RecoverPassword />
                    </LoggedOutMode>
                    } />

                    <Route path="/admin-dashboard" element={<LoggedOutMode>
                        <AdminDashboard />
                    </LoggedOutMode>
                    } />


                    <Route path="/patient-homepage" element={<PatientHomepage />} />
                    <Route path="/patient-dashboard" element={<LoggedInMode>
                        <PatientDashboard />
                    </LoggedInMode>
                    } />

                    <Route path="/doctor-dashboard" element={<LoggedInMode>
                        <DoctorDashboard />
                    </LoggedInMode>
                    } />

                    <Route path="/*" element={<ErrorPage />} />

                </Routes>
            </BrowserRouter>
        </div>
    )
}

export default PageRoutes;