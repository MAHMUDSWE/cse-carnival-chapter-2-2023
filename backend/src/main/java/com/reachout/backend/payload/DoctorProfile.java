package com.reachout.backend.payload;

import com.reachout.backend.entity.District;
import com.reachout.backend.entity.DoctorType;
import com.reachout.backend.entity.Role;
import com.reachout.backend.entity.Thana;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorProfile {
    private String title;
    private String firstName;
    private String lastName;
    private String username;
    private Date dob;
    private String gender;
    private String district;
    private String thana;
    private String nationId;
    private String bmdc;
    private String doctorType;
    private String phoneNumber;
    private String email;
    private String roles;
    private Boolean isApproved;
    private Set<String> specialization;
}
