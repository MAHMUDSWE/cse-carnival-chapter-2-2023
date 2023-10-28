package com.reachout.backend.repository;

import com.reachout.backend.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);

    boolean existsByUsername(String username);

    Boolean existsByUsernameOrEmail(String username, String email);
}
