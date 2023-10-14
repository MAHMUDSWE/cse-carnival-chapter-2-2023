const validateInputs = (inputs) => {

    const { name, email, username, password, phone } = inputs;
    let error = "";

    if (name && !name.trim()) {
        error = "Name is required";
        return error;
    } else if (name && name.trim().length > 30) {
        error = "Name must not exceed 30 characters";
        return error;
    }

    if (email && !email.trim()) {
        error = "Email is required";
        return error;
    } else if (email) {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            error = "Invalid email address";
            return error;
        }
    }

    if (username && !username.trim()) {
        error = "Username is required";
        return error;
    } else if (username && (username.trim().length < 4 || username.trim().length > 20)) {
        error = "Username must be between 4-20 characters";
        return error;
    }

    if (password && !password.trim()) {
        error = "Password is required";
        return error;
    } else if (password && (password.trim().length < 4 || password.trim().length > 20)) {
        error = "Password must be between 4-20 characters";
        return error;
    }

    if (phone && !phone.trim()) {
        error = "Phone is required";
        return error;
    } else if (phone && phone.trim().length !== 11) {
        error = "Phone number must be of 11 digits";
        return error;
    }
    // if (role && !role.trim()) {
    //     error = "Role is required";
    //     return error;
    // } else if (role && role.trim() !== "patient" || role.trim() !== "doctor" || role.trim() !== "admin") {
    //     error = "Role must be either Patient, Doctor or Admin";
    //     return error;
    // }

    return null;
};

export default validateInputs;