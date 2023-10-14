package com.reachout.backend.service;

import com.reachout.backend.entity.Admin;
import com.reachout.backend.entity.Doctor;
import com.reachout.backend.payload.LoginDto;

import java.util.List;

public interface AdminService {
    Admin authenticate(LoginDto loginDto);
    List<Doctor> getDoctorRegistrationRequest();
    boolean rejectDoctorRegistrationRequest(Long id);
    boolean approveDoctorRegistrationRequest(Long id);
}
