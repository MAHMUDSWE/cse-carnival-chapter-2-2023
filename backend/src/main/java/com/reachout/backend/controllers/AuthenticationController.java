package com.reachout.backend.controllers;

import com.reachout.backend.payload.AuthenticationResponse;
import com.reachout.backend.payload.LoginDto;
import com.reachout.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody LoginDto loginDto) {

        System.out.println("AUTHENTICATE (loging) :" + loginDto);
        return ResponseEntity.ok(authenticationService.authenticate(loginDto));
    }

}
