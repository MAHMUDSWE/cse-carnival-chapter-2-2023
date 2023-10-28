package com.reachout.backend.controllers;


import com.reachout.backend.entity.Doctor;
import com.reachout.backend.payload.LoginDto;
import com.reachout.backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/getRegistrationRequests")
    public ResponseEntity<List<Doctor>> getRegistrationRequests() {
        System.out.println("get Doctor Registration Requests");
        List<Doctor> doctorsReq = adminService.getDoctorRegistrationRequest();
        return new ResponseEntity<>(doctorsReq, HttpStatus.OK);
    }

    @PostMapping("/reject")
    public ResponseEntity<?> reject(Long id) {
        System.out.println("reject Doctor Registration Requests");
        boolean flag = adminService.rejectDoctorRegistrationRequest(id);
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }
}
