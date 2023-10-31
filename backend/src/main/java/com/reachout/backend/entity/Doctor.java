package com.reachout.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String firstName;
    private String lastName;
    private String username;
    private Date dob;
    private String gender;


    private String nationId;
    private String bmdc;

    //Many to one uni-directional relationship
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_type_id")  // Name of the foreign key column in the Doctor table
    private DoctorType doctorType;

    private String phoneNumber;
    private String email;
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "district_id")
    private District district;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)  // Many users can belong to one thana
    @JoinColumn(name = "thana_id")
    private Thana thana;

    private Boolean isApproved;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")  // Name of the foreign key column in the Patient table
    private Role role;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "doctors_and_specialization", // Name of the association table
            joinColumns = @JoinColumn(name = "doctor_id"), // Column in the association table for the Doctor entity
            inverseJoinColumns = @JoinColumn(name = "specialization_id") // Column in the association table for the DoctorSpecialization entity
    )
    private Set<DoctorSpecialization> specializations = new HashSet<>();
}
