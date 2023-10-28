package com.reachout.backend.service.impl;

import com.reachout.backend.entity.Patient;
import com.reachout.backend.entity.Role;
import com.reachout.backend.exception.BadRequestException;
import com.reachout.backend.exception.ResourceNotFoundException;
import com.reachout.backend.payload.ApiResponse;
import com.reachout.backend.payload.PatientProfile;
import com.reachout.backend.repository.PatientRepository;
import com.reachout.backend.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Patient addPatient(Patient patient) {
        System.out.println("user creation req service: " + patient);
            if (patientRepository.existsByUsername(patient.getUsername())) {
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

            if (patientRepository.existsByEmail(patient.getEmail())) {
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

           Role patientRole = new Role();
            patientRole.setName("PATIENT");
            //x
            patient.setRole(patientRole);

            //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return patientRepository.save(patient);
    }

    //dto introduce
    @Override
    public PatientProfile getPatientProfile(Long id) {

        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();

            PatientProfile.PatientProfileBuilder patientProfileBuilder = PatientProfile.builder()
                    .name(patient.getName())
                    .username(patient.getUsername())
                    .dob(patient.getDob())
                    .gender(patient.getGender())
                    .phoneNumber(patient.getPhoneNumber())
                    .isEnabled(patient.isEnabled())
                    .email(patient.getEmail());

            // Check if Thana is not null, then include it in the UserProfile
            if (patient.getThana() != null) {
                patientProfileBuilder.thana(patient.getThana().getName());
            } else {
                patientProfileBuilder.thana("Thana not specified"); // Or any default value you want
            }

            // Check if District is not null, then include it in the UserProfile
            if (patient.getDistrict() != null) {
                patientProfileBuilder.district(patient.getDistrict().getName());
            } else {
                patientProfileBuilder.district("District not specified"); // Or any default value you want
            }
            return patientProfileBuilder.build();

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
    public ApiResponse deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        patientRepository.deleteById(patient.getId());

        return ApiResponse.builder()
                .timeStamp(LocalDateTime.now().toString())
                .data(Map.of("user", "{}"))
                .message("user deleted")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

}

