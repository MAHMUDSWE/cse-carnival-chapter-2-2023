package com.reachout.backend.entity;

import com.reachout.backend.ApplicationUser;
import com.reachout.backend.entity.District;
import com.reachout.backend.entity.Thana;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Title;
    private String firstName;
    private String lastName;
    private String username;
    private Date dob;
    private String gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "district_id")  // Name of the foreign key column in the Doctor table
    private District district;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "thana_id")  // Name of the foreign key column in the Doctor table
    private Thana thana;

    private String nationId;
    private String bmdc;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_type_id")  // Name of the foreign key column in the Doctor table
    private DoctorType doctorType;
    private String phoneNumber;
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")  // Name of the foreign key column in the User table
    private Role role;

    private Boolean isApproved;

    @Override
    protected Collection<? extends GrantedAuthority> mapRolesToAuthority() {
        // Assuming a doctor has only one role
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role.getName())); // Assuming roles is a field in your Doctor class

        return authorities;
    }

    @Override
    @Transient
    public Set<Role> getRoles() {
        // Assuming a doctor has only one role
        return Collections.singleton(role);
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

    @Override
    public boolean isEnabled() {
        return false;
    }
}
