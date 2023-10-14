package com.reachout.backend.controllers;

import com.reachout.backend.entity.Doctor;
import com.reachout.backend.entity.User;
import com.reachout.backend.payload.ApiResponse;
import com.reachout.backend.payload.DoctorProfile;
import com.reachout.backend.payload.UserProfile;
import com.reachout.backend.service.DoctorService;
import com.reachout.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {

        System.out.println("in controller: doctor add: " + doctor);
        Doctor newDoctor = doctorService.addDoctor(doctor);

        return new ResponseEntity< >(doctor, HttpStatus.CREATED);
    }

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

        return null;
    }


}
