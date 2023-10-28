package com.reachout.backend.service;

import com.reachout.backend.entity.Patient;
import com.reachout.backend.payload.ApiResponse;
import com.reachout.backend.payload.PatientProfile;

public interface PatientService {
    Patient addPatient(Patient patient);

    PatientProfile getPatientProfile(Long id);

    ApiResponse deletePatient(Long id);
}
