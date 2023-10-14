package com.reachout.backend.service;

import com.reachout.backend.entity.User;
import com.reachout.backend.payload.ApiResponse;
import com.reachout.backend.payload.UserProfile;

import java.util.function.LongFunction;

public interface UserService {
    User addUser(User user);

    UserProfile getUserProfile(Long id);

    ApiResponse deleteUser(Long id);
}
