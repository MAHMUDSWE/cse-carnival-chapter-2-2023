package com.reachout.backend.security;

import com.reachout.backend.entity.Admin;
import com.reachout.backend.entity.Doctor;
import com.reachout.backend.entity.Patient;
import com.reachout.backend.repository.AdminRepository;
import com.reachout.backend.repository.DoctorRepository;
import com.reachout.backend.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("loadUserByUsername :-> " + username);

        // Check if the user is an admin
        if (adminRepository.existsByUsername(username)) {
            Admin admin = adminRepository.findByUsername(username);
            return new ApplicationUserAdmin(admin);
        }
        if (doctorRepository.existsByUsername(username)) {
            Doctor doctor = doctorRepository.findByUsername(username);
            return new ApplicationUserDoctor(doctor);
        } else if (patientRepository.existsByUsername(username)) {
            Patient patient = patientRepository.findByUsername(username);
            return new ApplicationUserPatient(patient);
        }
        throw new UsernameNotFoundException("username does not exists : " +  username);
    }
}

