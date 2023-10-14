package com.reachout.backend.service;

import com.reachout.backend.config.JwtService;
import com.reachout.backend.entity.Role;
import com.reachout.backend.entity.User;
import com.reachout.backend.exception.BadRequestException;
import com.reachout.backend.payload.ApiResponse;
import com.reachout.backend.payload.AuthenticationResponse;
import com.reachout.backend.payload.LoginDto;
import com.reachout.backend.payload.RegistrationDtoUser;
import com.reachout.backend.repository.RoleRepository;
import com.reachout.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthenticationResponse authenticate(LoginDto loginDto) {
        System.out.println("Auth Service : authentication : " + loginDto);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                loginDto.getPassword()));
        System.out.println("!!!\n");
        var user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).
                refreshToken(refreshToken).message(HttpStatus.OK.getReasonPhrase()).
                statusCode(HttpStatus.OK.value()).build();
    }

    public AuthenticationResponse register(RegistrationDtoUser registrationDtoUser) throws Exception {

        //check if user exists in DB
        if(userRepository.existsByUsernameOrEmail(registrationDtoUser.getUsername(), registrationDtoUser.getEmail())) {
            AuthenticationResponse response = AuthenticationResponse.builder().statusCode(HttpStatus.OK.value()).
                    message(HttpStatus.CONFLICT.getReasonPhrase()).
                    accessToken("").refreshToken("").build();

            throw  new BadRequestException(response);

        }

        var user = User.builder().name(registrationDtoUser.getName())
                .username(registrationDtoUser.getUsername())
                .email(registrationDtoUser.getEmail())
                .password(passwordEncoder.encode(registrationDtoUser.getPassword())).
                build();

        System.out.println("In auth service : reg ->" + user);

        //for email
        //user.setEnabled(false);

        //assigning to only single role
        /*
        Role role = roleRepository.findByName("USER").
                orElseThrow(()->new Exception("role USER cannot be fetched from DB"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);


        //user.setRoles(Collections.singleton(role));
        user.setRoles(userRoles);
        System.out.println("new user assigned role: " + role);



        // Fetch and assign roles
        Set<Role> roles = new HashSet<>();
        for(String roleName : registrationDto.getRoles()) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new Exception("Role not found: " + roleName));
            roles.add(role);
        }
        user.setRoles(roles);
        */

//        Role role = roleRepository.findByName("USER").orElseThrow(()->new Exception("Role not found"));
        Role x = new Role();
        x.setName("USER");
        user.setRoles(x);
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder().statusCode(HttpStatus.OK.value()).
                message(HttpStatus.OK.getReasonPhrase()).
                accessToken(jwtToken).refreshToken(refreshToken).build();
    }

}
