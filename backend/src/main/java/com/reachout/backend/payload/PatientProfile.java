package com.reachout.backend.payload;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Builder
@Getter
@Setter
public class PatientProfile {

    private String name;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String district;
    private String thana;
    private String gender;
    private Date dob;
    private boolean isEnabled;

}
