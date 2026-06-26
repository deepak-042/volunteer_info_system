package com.deepak.volunteer_info_system.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class signupRequest {
    // Auth Details
    private String email;
    private String password;

    // Profile Details
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String skills;
    private String availability;
}
