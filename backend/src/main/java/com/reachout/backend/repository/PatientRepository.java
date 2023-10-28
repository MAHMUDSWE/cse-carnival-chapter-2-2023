package com.reachout.backend.repository;

import com.reachout.backend.entity.Doctor;
import com.reachout.backend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUsername(String username);
    Optional<Patient> findByUsernameOrEmail(String username, String email);
    Optional<Patient> findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsernameOrEmail(String username, String email);
}
