package com.reachout.backend.controllers;

import com.reachout.backend.entity.Doctor;
import com.reachout.backend.payload.ApiResponse;
import com.reachout.backend.payload.DoctorAllResponse;
import com.reachout.backend.payload.DoctorProfile;
import com.reachout.backend.service.DoctorService;
import com.reachout.backend.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<DoctorProfile> getUserProfile(@PathVariable(value = "id")
                                                      Long id) throws Exception {
        System.out.println("get doctor, id: " + id);
        DoctorProfile doctorProfile = doctorService.getDoctorProfile(id);

        return new ResponseEntity< >(doctorProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DOCTOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> deleteDoctor(@PathVariable(value = "id") Long id) {
        ApiResponse apiResponse = doctorService.deleteDoctor(id);

        return new ResponseEntity< >(apiResponse, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Doctor> updateDoctor(@PathVariable(value = "id") Long id, @RequestBody Doctor doctor,
                                          @AuthenticationPrincipal UserDetails currentUserDetails) {

        System.out.println("doctorController : updateDoctor");
        Doctor updatedDoctor = doctorService.updateDoctor(id, doctor, currentUserDetails);
        return new ResponseEntity<>(updatedDoctor, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DoctorAllResponse> getAllDoctor(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NO, required = false) int pageNo,
                                                     @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                                     @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                                     @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        DoctorAllResponse doctorAll = doctorService.getAllDoctors(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(doctorAll, HttpStatus.OK);
    }


}
