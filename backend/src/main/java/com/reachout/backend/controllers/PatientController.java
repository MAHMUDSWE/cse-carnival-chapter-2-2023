package com.reachout.backend.controllers;


import com.reachout.backend.entity.Patient;
import com.reachout.backend.payload.ApiResponse;
import com.reachout.backend.payload.PatientProfile;
import com.reachout.backend.service.AuthenticationService;
import com.reachout.backend.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/{id}")
    public ResponseEntity<PatientProfile> getPatientProfile(@PathVariable(value = "id")
                                                   Long id) throws Exception {
        System.out.println("get Patient id: " + id);
        PatientProfile PatientProfile = patientService.getPatientProfile(id);

        return new ResponseEntity< >(PatientProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('Patient') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deletePatient(@PathVariable(value = "id") Long id) {
        ApiResponse apiResponse = patientService.deletePatient(id);

        return new ResponseEntity< >(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable(value = "id") Long id) {

        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPatient() {

        return null;
    }

}
