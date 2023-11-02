package com.reachout.backend.service;

import com.reachout.backend.config.JwtService;
import com.reachout.backend.entity.*;

import com.reachout.backend.exception.BadRequestException;
import com.reachout.backend.payload.AuthenticationResponse;
import com.reachout.backend.payload.LoginDto;
import com.reachout.backend.payload.RegistrationDtoPatient;
import com.reachout.backend.repository.*;
import com.reachout.backend.security.ApplicationUserAdmin;
import com.reachout.backend.security.ApplicationUserDoctor;
import com.reachout.backend.security.ApplicationUserPatient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final PatientRepository patientRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AdminRepository adminRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorTypeRepository doctorTypeRepository;
    private final DistrictRepository districtRepository;
    private final ThanaRepository thanaRepository;
    private final DoctorSpecializationRepository  doctorSpecializationRepository;

    public AuthenticationResponse authenticate(LoginDto loginDto) {
        System.out.println("Auth Service : authentication : " + loginDto);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                loginDto.getPassword()));
        System.out.println("!!!\n");

        if(patientRepository.existsByUsername(loginDto.getUsername())) {
            var user = patientRepository.findByUsername(loginDto.getUsername());
            ApplicationUserPatient applicationUserPatient = new ApplicationUserPatient(user);
            var jwtToken = jwtService.generateToken(applicationUserPatient);
            var refreshToken = jwtService.generateRefreshToken(applicationUserPatient);
            return AuthenticationResponse.builder().accessToken(jwtToken).
                    refreshToken(refreshToken).message(HttpStatus.OK.getReasonPhrase()).
                    statusCode(HttpStatus.OK.value()).build();
        }
        if(adminRepository.existsByUsername(loginDto.getUsername())) {
            var admin = adminRepository.findByUsername(loginDto.getUsername());
            ApplicationUserAdmin applicationUserAdmin = new ApplicationUserAdmin(admin);
            var jwtToken = jwtService.generateToken(applicationUserAdmin);
            var refreshToken = jwtService.generateRefreshToken(applicationUserAdmin);
            return AuthenticationResponse.builder().accessToken(jwtToken).
                    refreshToken(refreshToken).message(HttpStatus.OK.getReasonPhrase()).
                    statusCode(HttpStatus.OK.value()).build();
        }
        if(doctorRepository.existsByUsername(loginDto.getUsername())) {
            var doctor = doctorRepository.findByUsername(loginDto.getUsername());
            ApplicationUserDoctor applicationUserDoctor = new ApplicationUserDoctor(doctor);
            var jwtToken = jwtService.generateToken(applicationUserDoctor);
            var refreshToken = jwtService.generateRefreshToken(applicationUserDoctor);
            return AuthenticationResponse.builder().accessToken(jwtToken).
                    refreshToken(refreshToken).message(HttpStatus.OK.getReasonPhrase()).
                    statusCode(HttpStatus.OK.value()).build();
        }
        //to do
        return null;
    }

    boolean checkEmailAndUsername(String username, String email) {
        return patientRepository.existsByUsernameOrEmail(username, email)
                || doctorRepository.existsByUsernameOrEmail(username, email)
                || adminRepository.existsByUsernameOrEmail(username, email);
    }
    public AuthenticationResponse register(RegistrationDtoPatient registrationDtoUser) throws Exception {

        //check if user exists in DB -> Patient, Doctor, Admin
        if(checkEmailAndUsername(registrationDtoUser.getUsername(), registrationDtoUser.getEmail())) {
            AuthenticationResponse response = AuthenticationResponse.builder().statusCode(HttpStatus.OK.value()).
                    message(HttpStatus.CONFLICT.getReasonPhrase()).
                    accessToken("").refreshToken("").build();

            throw  new BadRequestException(response);

        }

        var patient = Patient.builder().name(registrationDtoUser.getName())
                .username(registrationDtoUser.getUsername())
                .email(registrationDtoUser.getEmail())
                .password(passwordEncoder.encode(registrationDtoUser.getPassword())).
                build();

        System.out.println("In auth service : reg ->" + patient);

        //for email
        //user.setEnabled(false);

        //assigning to only single role
        Role role = roleRepository.findByName("ROLE_PATIENT").
                orElseThrow(()->new Exception("role ROLE_PATIENT cannot be fetched from DB"));


        patient.setRole(role);
        System.out.println("new user assigned role: " + role);
        patientRepository.save(patient);

        ApplicationUserPatient applicationUserPatient = new ApplicationUserPatient(patient);
        var jwtToken = jwtService.generateToken(applicationUserPatient);
        var refreshToken = jwtService.generateRefreshToken(applicationUserPatient);
        return AuthenticationResponse.builder().statusCode(HttpStatus.OK.value()).
                message(HttpStatus.OK.getReasonPhrase()).
                accessToken(jwtToken).refreshToken(refreshToken).build();
    }

    public AuthenticationResponse addDoctor(Doctor doctor) throws Exception {

        //check if user exists in DB
        if(checkEmailAndUsername(doctor.getUsername(), doctor.getEmail())) {
            AuthenticationResponse response = AuthenticationResponse.builder().statusCode(HttpStatus.CONFLICT.value()).
                    message(HttpStatus.CONFLICT.getReasonPhrase()).
                    accessToken("").refreshToken("").build();
            throw  new BadRequestException(response);
        }

        DoctorType managedDoctorType = doctorTypeRepository.findById(doctor.getDoctorType().getId()).orElseThrow(
                () -> new Exception("doctor type not found in DB")
        );

        District managedDistrict = districtRepository.findById(doctor.getDistrict().getId()).orElseThrow(
                () -> new Exception("district not found in DB")
        );

        Thana managedThana = thanaRepository.findById(doctor.getThana().getId()).orElseThrow(
                () -> new Exception("thana not found in DB")
        );

         //Create a set of DoctorSpecialization entities
        Set<DoctorSpecialization> specializations = new HashSet<>();
        for (DoctorSpecialization x : doctor.getSpecializations()) {
            DoctorSpecialization specialization = DoctorSpecialization.builder()
                    .name(x.getName())
                    .build();
            specializations.add(specialization);
            doctorSpecializationRepository.save(specialization); // Save DoctorSpecialization

        }

        Doctor newDoctor = Doctor.builder().
                title(doctor.getTitle()).
                firstName(doctor.getFirstName()).
                lastName(doctor.getLastName()).
                phoneNumber(doctor.getPhoneNumber()).
                isApproved(false).
                doctorType(managedDoctorType).
                bmdc(doctor.getBmdc()).
                district(managedDistrict).
                thana(managedThana).
                nationId(doctor.getNationId()).
                dob(doctor.getDob()).
                gender(doctor.getGender())
                .username(doctor.getUsername())
                .email(doctor.getEmail())
                .password(passwordEncoder.encode(doctor.getPassword())).
                build();

        // Assign the specializations to the newDoctor
        newDoctor.setSpecializations(specializations);

        System.out.println("In auth service : reg doc ->" + doctor);

        //assigning to only single role
        Role role = roleRepository.findByName("ROLE_DOCTOR").
                orElseThrow(()->new Exception("role DOCTOR cannot be fetched from DB"));

        newDoctor.setRole(role);
        System.out.println("new user assigned role: " + role);
        doctorRepository.save(newDoctor);

        ApplicationUserDoctor applicationUserDoctor = new ApplicationUserDoctor(newDoctor);
        var jwtToken = jwtService.generateToken(applicationUserDoctor);
        var refreshToken = jwtService.generateRefreshToken(applicationUserDoctor);
        return AuthenticationResponse.builder().statusCode(HttpStatus.OK.value()).
                message(HttpStatus.OK.getReasonPhrase()).
                accessToken(jwtToken).refreshToken(refreshToken).build();
    }
}
