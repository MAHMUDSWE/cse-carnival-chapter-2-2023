package com.reachout.backend.service.impl;

import com.reachout.backend.entity.Role;
import com.reachout.backend.entity.User;
import com.reachout.backend.exception.BadRequestException;
import com.reachout.backend.exception.ResourceNotFoundException;
import com.reachout.backend.payload.ApiResponse;
import com.reachout.backend.payload.UserProfile;
import com.reachout.backend.repository.UserRepository;
import com.reachout.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User addUser(User user) {
        System.out.println("user creation req service: " + user);
            if (userRepository.existsByUsername(user.getUsername())) {
                System.out.println("username not available");
                ApiResponse apiResponse =  ApiResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("new_registered_user", "{}"))
                        .message("User name taken")
                        .status(HttpStatus.CONFLICT)
                        .statusCode(HttpStatus.CONFLICT.value())
                        .build();
                throw new BadRequestException(apiResponse);
            }

            if (userRepository.existsByEmail(user.getEmail())) {
                System.out.println("email not available");
                ApiResponse apiResponse =  ApiResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("new_registered_user", "{}"))
                        .message("Email already taken")
                        .status(HttpStatus.CONFLICT)
                        .statusCode(HttpStatus.CONFLICT.value())
                        .build();
                throw new BadRequestException(apiResponse);
            }

           Role userRole = new Role();
            userRole.setName("USER");
            //x
            user.setRoles(userRole);

            //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    //dto introduce
    @Override
    public UserProfile getUserProfile(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            UserProfile.UserProfileBuilder userProfileBuilder = UserProfile.builder()
                    .name(user.getName())
                    .username(user.getUsername())
                    .dob(user.getDob())
                    .gender(user.getGender())
                    .phoneNumber(user.getPhoneNumber())
                    .isEnabled(user.isEnabled())
                    .email(user.getEmail());

            // Check if Thana is not null, then include it in the UserProfile
            if (user.getThana() != null) {
                userProfileBuilder.thana(user.getThana().getName());
            } else {
                userProfileBuilder.thana("Thana not specified"); // Or any default value you want
            }

            // Check if District is not null, then include it in the UserProfile
            if (user.getDistrict() != null) {
                userProfileBuilder.district(user.getDistrict().getName());
            } else {
                userProfileBuilder.district("District not specified"); // Or any default value you want
            }
            return userProfileBuilder.build();

        } else {
            System.out.println("getUserProfile: user does not exists " + id);
            ApiResponse apiResponse = ApiResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .data(Map.of("user", "{}"))
                    .message("user does not exists")
                    .status(HttpStatus.BAD_REQUEST)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
            throw new BadRequestException(apiResponse);
        }
    }

    @Override
    public ApiResponse deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userRepository.deleteById(user.getId());

        return ApiResponse.builder()
                .timeStamp(LocalDateTime.now().toString())
                .data(Map.of("user", "{}"))
                .message("user deleted")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

}

