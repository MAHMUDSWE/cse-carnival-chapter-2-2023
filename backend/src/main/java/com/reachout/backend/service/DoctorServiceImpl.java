package com.reachout.backend.service;


import com.reachout.backend.entity.Doctor;
import com.reachout.backend.entity.Role;
import com.reachout.backend.exception.BadRequestException;
import com.reachout.backend.payload.ApiResponse;
import com.reachout.backend.payload.DoctorProfile;
import com.reachout.backend.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{
    private final DoctorRepository doctorRepository;

    @Override
    public Doctor addDoctor(Doctor doctor) {
        System.out.println("doctor creation req service: " + doctor);

        if (doctorRepository.existsByUsername(doctor.getUsername())) {
            System.out.println("username not available");
            ApiResponse apiResponse =  ApiResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .data(Map.of("new_registered_doctor", "{}"))
                    .message("User name taken")
                    .status(HttpStatus.CONFLICT)
                    .statusCode(HttpStatus.CONFLICT.value())
                    .build();
            throw new BadRequestException(apiResponse);
        }

        if (doctorRepository.existsByEmail(doctor.getEmail())) {
            System.out.println("email not available");
            ApiResponse apiResponse =  ApiResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .data(Map.of("new_registered_doctor", "{}"))
                    .message("Email already taken")
                    .status(HttpStatus.CONFLICT)
                    .statusCode(HttpStatus.CONFLICT.value())
                    .build();
            throw new BadRequestException(apiResponse);
        }

        Role doctorRole = new Role();
        doctorRole.setName("DOCTOR");
        //x
        doctor.setRole(doctorRole);

        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return doctorRepository.save(doctor);
    }

    @Override
    public DoctorProfile getDoctorProfile(Long id) {

        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);

        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            return DoctorProfile.builder()
                    .firstName(doctor.getFirstName())
                    .lastName(doctor.getLastName())
                    .username(doctor.getUsername())
                    .dob(doctor.getDob())
                    .gender(doctor.getGender())
                    .phoneNumber(doctor.getPhoneNumber())
                    .isApproved(doctor.getIsApproved())
                    .email(doctor.getEmail())
                    .thana(doctor.getThana().getName())
                    .district(doctor.getDistrict().getName())
                    .doctorType(doctor.getDoctorType().getName())
                    .roles(doctor.getRole().getName())
                    .nationId(doctor.getNationId())
                    .bmdc(doctor.getBmdc())
                    .title(doctor.getTitle())
                    .isApproved(false)
                    .build();
        } else {
            System.out.println("getDoctorProfile: doctor does not exists " + id);
            ApiResponse apiResponse = ApiResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .data(Map.of("doctor", "{}"))
                    .message("doctor does not exists")
                    .status(HttpStatus.BAD_REQUEST)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
            throw new BadRequestException(apiResponse);
        }
    }

    @Override
    public ApiResponse deleteDoctor(Long id) {
        return null;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }


}
