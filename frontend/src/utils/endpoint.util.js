const apiEndpoint = {
    base: import.meta.env.VITE_API,
    auth: {
        signup: "/user/signup",
        login: "/user/login",
    },
};

export default apiEndpoint;