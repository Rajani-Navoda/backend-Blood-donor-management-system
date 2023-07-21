package com.blood.donation.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Blood_donor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Donor_ID;
    private String firstName;
    private String lastName;
    private String fullName;
    private String NIC;
    private LocalDate DOB;
    private Gender gender;
    private String address;
    private String city;
    private String postalCode;
    private String email;
    private String contactMobile;
    private String contactHome;
    private BloodGroup bloodGroup;
    private int height;
    private int weight;
    private double BMI;
    private String specialConditions;

}
