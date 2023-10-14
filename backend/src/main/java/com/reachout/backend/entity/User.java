package com.reachout.backend.entity;

import com.reachout.backend.ApplicationUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"}),
})
public class User extends ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "district_id")  // Name of the foreign key column in the Doctor table
    private District district;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "thana_id")  // Name of the foreign key column in the Doctor table
    private Thana thana;

    private String gender;
    private Date dob;
    private boolean isEnabled;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")  // Name of the foreign key column in the User table
    private Role roles;

    @Override
    protected Collection<? extends GrantedAuthority> mapRolesToAuthority() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(roles.getName()));

        return authorities;
    }

    @Override
    @Transient
    public Set<Role> getRoles() {
        // Assuming a user has only one role
        return Collections.singleton(roles);
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        for (Role x: roles) {
//            System.out.println("roles: " + x.getName());
//        }
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (Role role: roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
    @Override
    public boolean isEnabled() {
        return true;
    }
}

