package com.reachout.backend.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationDtoPatient {
    private String name;
    private String email;
    private String username;
    private String password;


    private boolean isEnabled;
}

