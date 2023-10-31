package com.reachout.backend.repository;

import com.reachout.backend.entity.DoctorSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorSpecializationRepository extends JpaRepository<DoctorSpecialization, Long> {
}
