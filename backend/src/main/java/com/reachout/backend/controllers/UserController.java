package com.reachout.backend.controllers;


import com.reachout.backend.entity.User;
import com.reachout.backend.payload.ApiResponse;
import com.reachout.backend.payload.AuthenticationResponse;
import com.reachout.backend.payload.RegistrationDtoUser;
import com.reachout.backend.payload.UserProfile;
import com.reachout.backend.service.AuthenticationService;
import com.reachout.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

//    //@valid
//    @PostMapping("/register")
//    public ResponseEntity<User> addUser(@RequestBody User user) {
//        User newUser = userService.addUser(user);
//
//        return new ResponseEntity< >(newUser, HttpStatus.CREATED);
//    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationDtoUser registrationDtoUser)
            throws Exception {
        System.out.println("Registration Req: " + registrationDtoUser);
        AuthenticationResponse response = authenticationService.register(registrationDtoUser);
        System.out.println("registration response : " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable(value = "id")
                                                   Long id) throws Exception {
        System.out.println("get user id: " + id);
        UserProfile userProfile = userService.getUserProfile(id);

        return new ResponseEntity< >(userProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "id") Long id) {
        ApiResponse apiResponse = userService.deleteUser(id);

        return new ResponseEntity< >(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(value = "id") Long id) {

        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUser() {

        return null;
    }

}
