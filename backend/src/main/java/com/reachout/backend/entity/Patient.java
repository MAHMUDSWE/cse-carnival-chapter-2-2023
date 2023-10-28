package com.reachout.backend.entity;

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
@Table(name = "patients", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"}),
})
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.EAGER)  // Many users can belong to one district
    @JoinColumn(name = "district_id")
    private District district;


    @ManyToOne(fetch = FetchType.EAGER)  // Many users can belong to one thana
    @JoinColumn(name = "thana_id")
    private Thana thana;

    private String gender;
    private Date dob;
    private boolean isEnabled;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")  // Name of the foreign key column in the Patient table
    private Role role;

}

