package com.blood.donation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDonorRequestDTO {

    private String firstName;
    private String lastName;
    private String fullName;
    private String gender;
    private String address;
    private String city;
    private String postalCode;
    private String email;
    private String contactMobile;
    private String contactHome;
    private double height;
    private double weight;
    private double bmi;
    private String specialConditions;
}
