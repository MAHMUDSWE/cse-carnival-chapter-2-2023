package com.reachout.backend.security;

import com.reachout.backend.entity.Admin;
import com.reachout.backend.entity.Doctor;
import com.reachout.backend.entity.Patient;
import com.reachout.backend.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class ApplicationUserAdmin implements UserDetails {
    private final Admin admin;

    public ApplicationUserAdmin(Admin admin) {
        this.admin = admin;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(admin.getRole().getName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
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
        return admin.getId();
    }
}

