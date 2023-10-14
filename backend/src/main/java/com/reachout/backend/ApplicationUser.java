package com.reachout.backend;

import com.reachout.backend.entity.Role;
import jakarta.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class ApplicationUser implements UserDetails {

    // Common fields and methods for both Doctor and User

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Implement this method to return the roles for the specific user type
        return mapRolesToAuthority();
    }

    protected abstract Collection<? extends GrantedAuthority> mapRolesToAuthority();

    @Transient
    public abstract Set<Role> getRoles();
}

