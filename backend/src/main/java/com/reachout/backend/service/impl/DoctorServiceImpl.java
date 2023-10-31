package com.reachout.backend.service.impl;


import com.reachout.backend.entity.Doctor;
import com.reachout.backend.entity.DoctorSpecialization;
import com.reachout.backend.entity.Role;
import com.reachout.backend.exception.BadRequestException;
import com.reachout.backend.exception.ResourceNotFoundException;
import com.reachout.backend.exception.UnauthorizedException;
import com.reachout.backend.payload.ApiResponse;
import com.reachout.backend.payload.DoctorAllResponse;
import com.reachout.backend.payload.DoctorProfile;
import com.reachout.backend.payload.SimpleResponse;
import com.reachout.backend.repository.DoctorRepository;
import com.reachout.backend.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
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
            Set<String> doctorSpecilization = new HashSet<>();
            for (DoctorSpecialization it: doctor.getSpecializations()) {
                doctorSpecilization.add(it.getName());
            }
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
                    .isApproved(doctor.getIsApproved())
                    .specialization(doctorSpecilization)
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
    public Doctor updateDoctor(Long id, Doctor doctor, UserDetails currentUserDetails) {
        Optional<Doctor> existingDoctorOptional = doctorRepository.findById(id);

        // Get the currently authenticated user from the security context.
        // Check if the currently authenticated user is the same user as the Doctor being updated.
        if (!currentUserDetails.getUsername().equals(doctor.getUsername())) {
            SimpleResponse apiResponse = new SimpleResponse(HttpStatus.NOT_FOUND.value(),
                    "You don't have permission to update profile of: " + id);
            throw new UnauthorizedException(apiResponse);
        }

        // Update the Doctor object with the new values.
        Doctor existingDoctor = doctorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Doctor with", "id", id)
        );
        existingDoctor.setTitle(doctor.getTitle());
        existingDoctor.setFirstName(doctor.getFirstName());
        existingDoctor.setLastName(doctor.getLastName());
        existingDoctor.setDob(doctor.getDob());
        existingDoctor.setGender(doctor.getGender());
        existingDoctor.setNationId(doctor.getNationId());
        existingDoctor.setBmdc(doctor.getBmdc());
        existingDoctor.setDoctorType(doctor.getDoctorType());
        existingDoctor.setPhoneNumber(doctor.getPhoneNumber());
        existingDoctor.setEmail(doctor.getEmail());
        existingDoctor.setPassword(doctor.getPassword());
        existingDoctor.setDistrict(doctor.getDistrict());
        existingDoctor.setThana(doctor.getThana());
        existingDoctor.setIsApproved(doctor.getIsApproved());
        existingDoctor.setRole(doctor.getRole());
        existingDoctor.setSpecializations(doctor.getSpecializations());

        // Save the Doctor object back to the database.
        return doctorRepository.save(existingDoctor);
    }
    @Override
    public ApiResponse deleteDoctor(Long id) {
        return null;
    }

    @Override
    public DoctorAllResponse getAllDoctors(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Doctor> doctors = doctorRepository.findAll(pageable);
        List<Doctor> doctorList = doctors.getContent();

        DoctorAllResponse res = new DoctorAllResponse();
        res.setDoctors(doctorList);
        res.setPageNo(doctors.getNumber());
        res.setPageSize(doctors.getSize());
        res.setTotalElements(doctors.getTotalElements());
        res.setTotalPages(doctors.getTotalPages());
        res.setLast(doctors.isLast());
        return res;
    }


}
