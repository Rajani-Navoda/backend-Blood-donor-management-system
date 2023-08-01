package com.blood.donation.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrganizerRequestDTO {

    private String firstName;
    private String lastName;
    private String fullName;
    private String address;
    private String city;
    private String email;
    private String contactMobile;
    private String contactHome;

}
