package com.reachout.backend.service;

import com.reachout.backend.entity.Doctor;
import com.reachout.backend.payload.ApiResponse;
import com.reachout.backend.payload.DoctorAllResponse;
import com.reachout.backend.payload.DoctorProfile;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface DoctorService {
    Doctor addDoctor(Doctor doctor);

    DoctorProfile getDoctorProfile(Long id);

    Doctor updateDoctor(Long id, Doctor doctor, UserDetails currentUserDetails);

    ApiResponse deleteDoctor(Long id);

    DoctorAllResponse getAllDoctors(int pageNo, int pageSize, String sortBy, String sortDir);

}
