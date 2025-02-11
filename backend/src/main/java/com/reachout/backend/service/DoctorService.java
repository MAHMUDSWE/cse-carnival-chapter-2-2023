package com.reachout.backend.service;

import com.reachout.backend.entity.Doctor;
import com.reachout.backend.payload.ApiResponse;
import com.reachout.backend.payload.DoctorProfile;

import java.util.List;

public interface DoctorService {
    Doctor addDoctor(Doctor doctor);

    DoctorProfile getDoctorProfile(Long id);

    ApiResponse deleteDoctor(Long id);

    List<Doctor> getAllDoctors();
}
