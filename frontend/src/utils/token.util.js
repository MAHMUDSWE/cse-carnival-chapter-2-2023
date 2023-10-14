
const getAccessToken = () => {
    // const token = localStorage.getItem("access_token");
    // return token;
    return true;
}

const storeAccessToken = (access_token) => {
    localStorage.setItem('access_token', access_token);
};

const removeAccessToken = () => {
    localStorage.removeItem('access_token');
};

const getUserRole = () => {
    const role = localStorage.getItem("user_role");
    return role;
}

export { getAccessToken, storeAccessToken, removeAccessToken, getUserRole };
