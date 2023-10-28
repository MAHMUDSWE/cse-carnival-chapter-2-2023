package com.reachout.backend.controllers;

import com.reachout.backend.entity.Doctor;
import com.reachout.backend.payload.ApiResponse;
import com.reachout.backend.payload.DoctorProfile;
import com.reachout.backend.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/{id}")
    public ResponseEntity<DoctorProfile> getUserProfile(@PathVariable(value = "id")
                                                      Long id) throws Exception {
        System.out.println("get doctor, id: " + id);
        DoctorProfile doctorProfile = doctorService.getDoctorProfile(id);

        return new ResponseEntity< >(doctorProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteDoctor(@PathVariable(value = "id") Long id) {
        ApiResponse apiResponse = doctorService.deleteDoctor(id);

        return new ResponseEntity< >(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable(value = "id") Long id) {

        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllDoctor() {
        List<Doctor>  doctors = doctorService.getAllDoctors();

        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }


}
