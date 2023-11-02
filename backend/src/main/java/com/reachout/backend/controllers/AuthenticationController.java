package com.reachout.backend.controllers;

import com.reachout.backend.entity.Doctor;
import com.reachout.backend.payload.AuthenticationResponse;
import com.reachout.backend.payload.LoginDto;
import com.reachout.backend.payload.RegistrationDtoPatient;
import com.reachout.backend.service.AdminService;
import com.reachout.backend.service.AuthenticationService;
import com.reachout.backend.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final DoctorService doctorService;
    private final AdminService adminService;

    @CrossOrigin
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody LoginDto loginDto) {

        System.out.println("AUTHENTICATE (loging) :" + loginDto);
        return ResponseEntity.ok(authenticationService.authenticate(loginDto));
    }

    @PostMapping("/register/patient")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegistrationDtoPatient registrationDtoUser)
            throws Exception {
        System.out.println("Registration Req: " + registrationDtoUser);
        AuthenticationResponse response = authenticationService.register(registrationDtoUser);
        System.out.println("registration response : " + response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("v2/register/doctor")
    public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor) throws Exception {

        System.out.println("in controller: doctor add: " + doctor);
        AuthenticationResponse response = authenticationService.addDoctor(doctor);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/admin/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        System.out.println("Registration Req: " + loginDto);
        AuthenticationResponse response = authenticationService.authenticate(loginDto);
        System.out.println("registration response : " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/test")
    public String test() {
        return "ok";
    }

}
