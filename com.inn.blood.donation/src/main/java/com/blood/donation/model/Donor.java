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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer donorId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image profileImage;

//    @Column(nullable = false)
    private String fullName;

//    @Column(nullable = false)
    private String nic;

//    @Column(nullable = false)
    private LocalDate dob;

//    @Column(nullable = false)
    private String gender;

//    @Column(nullable = false)
    private String address;

//    @Column(nullable = false)
    private String city;

//    @Column(nullable = false)
    private String postalCode;

//    @Column(nullable = false)
    private String contactMobile;

    private String contactHome;

//    @Column(nullable = false)
    private String bloodGroup;

//    @Column(nullable = false)
    private double height;

//    @Column(nullable = false)
    private double weight;

//    @Column(nullable = false)
    private double bmi;

    private String specialConditions;

}
