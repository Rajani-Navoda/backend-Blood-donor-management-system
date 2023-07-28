package com.blood.donation.dto;

import com.blood.donation.model.BloodGroup;
import com.blood.donation.model.Gender;
import com.blood.donation.model.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonorRegisterRequestDTO {

    private String fullName;
    private String nic;
    private LocalDate dob;
    private String gender;
    private String address;
    private String city;
    private String postalCode;
    private String contactMobile;
    private String contactHome;
    private String bloodGroup;
    private double height;
    private double weight;
    private double bmi;
    private String specialConditions;
}
