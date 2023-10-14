package com.reachout.backend.service.impl;

import com.reachout.backend.entity.Admin;
import com.reachout.backend.entity.Doctor;
import com.reachout.backend.payload.LoginDto;
import com.reachout.backend.exception.BadRequestException;
import com.reachout.backend.payload.ApiResponse;
import com.reachout.backend.repository.AdminRepository;
import com.reachout.backend.repository.DoctorRepository;
import com.reachout.backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final DoctorRepository doctorRepository;
    private final AdminRepository adminRepository;
    @Override
    public Admin authenticate(LoginDto loginDto) {
        return null;
    }

    @Override
    public List<Doctor> getDoctorRegistrationRequest() {
        return doctorRepository.findAll();
    }

    @Override
    public boolean rejectDoctorRegistrationRequest(Long id) {
        if(!doctorRepository.existsById(id)) {
            System.out.println("reject Doctor Registration Request: does not exists " + id);
            ApiResponse apiResponse = ApiResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .data(Map.of("doctor", "{}"))
                    .message("doctor does not exists")
                    .status(HttpStatus.BAD_REQUEST)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
            throw new BadRequestException(apiResponse);
        }
        return true;
    }

    @Override
    public boolean approveDoctorRegistrationRequest(Long id) {
        return false;
    }
}
