package com.reachout.backend.security;

import com.reachout.backend.entity.Doctor;
import com.reachout.backend.entity.Patient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ApplicationUserPatient implements UserDetails {
    private final Patient patient;

    public ApplicationUserPatient(Patient patient) {
        this.patient = patient;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(patient.getRole().getName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return patient.getPassword();
    }

    @Override
    public String getUsername() {
        return patient.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Implement account expiration logic based on the user's type
        // Return true if the account is not expired
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Implement account locking logic based on the user's type
        // Return true if the account is not locked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Implement credentials expiration logic based on the user's type
        // Return true if the credentials are not expired
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Implement user enablement logic based on the user's type
        // Return true if the user is enabled
        return true;
    }

    public Long getId() {
        return patient.getId();
    }
}
