package com.reachout.backend.payload;

import com.reachout.backend.entity.District;
import com.reachout.backend.entity.Role;
import com.reachout.backend.entity.Thana;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Builder
@Getter
@Setter
public class UserProfile {

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
