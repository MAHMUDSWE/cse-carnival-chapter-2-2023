package com.reachout.backend.repository;

import com.reachout.backend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Doctor findByUsername(String username);
    List<Doctor> findAll();

    Boolean existsByUsernameOrEmail(String username, String email);
}
