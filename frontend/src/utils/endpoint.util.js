const apiEndpoint = {
    base: import.meta.env.VITE_API,
    auth: {
        signup: "/auth/register",
        doctorSignup: "/auth/register/doctor",
        login: "/auth/authenticate",
        doctorLogin: ""
    },
};

export default apiEndpoint;