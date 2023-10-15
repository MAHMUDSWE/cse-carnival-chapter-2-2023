package com.reachout.backend.controllers;

import com.reachout.backend.entity.Doctor;
import com.reachout.backend.payload.AuthenticationResponse;
import com.reachout.backend.payload.LoginDto;
import com.reachout.backend.payload.RegistrationDtoUser;
import com.reachout.backend.service.AuthenticationService;
import com.reachout.backend.service.DoctorService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final DoctorService doctorService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody LoginDto loginDto) {

        System.out.println("AUTHENTICATE (loging) :" + loginDto);
        return ResponseEntity.ok(authenticationService.authenticate(loginDto));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationDtoUser registrationDtoUser)
            throws Exception {
        System.out.println("Registration Req: " + registrationDtoUser);
        AuthenticationResponse response = authenticationService.register(registrationDtoUser);
        System.out.println("registration response : " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register/doctor")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {

        System.out.println("in controller: doctor add: " + doctor);
        Doctor newDoctor = doctorService.addDoctor(doctor);

        return new ResponseEntity< >(doctor, HttpStatus.CREATED);

    }

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

}
