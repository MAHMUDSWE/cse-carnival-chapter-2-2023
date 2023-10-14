package com.reachout.backend.security;

import com.reachout.backend.ApplicationUser;
import com.reachout.backend.entity.Doctor;
import com.reachout.backend.entity.User;
import com.reachout.backend.repository.DoctorRepository;
import com.reachout.backend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;

    public CustomUserDetailsService(UserRepository userRepository, DoctorRepository doctorRepository) {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("loadUserByUsername :-> " + username);

        // Check if the user is a doctor
        Doctor doctor = doctorRepository.findByUsername(username);
        if(doctor == null) {
            System.out.println("NOT A DOCTOR");
        }
        if (doctor != null) {
            return buildUserDetails(doctor);
        }

        System.out.println("loadByUserName: " + username);

        // Check if the user is a regular user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return buildUserDetails(user);
    }

    private UserDetails buildUserDetails(ApplicationUser applicationUser) {
        return new org.springframework.security.core.userdetails.User(
                applicationUser.getUsername(),
                applicationUser.getPassword(),
                applicationUser.getAuthorities()
        );
    }
}

