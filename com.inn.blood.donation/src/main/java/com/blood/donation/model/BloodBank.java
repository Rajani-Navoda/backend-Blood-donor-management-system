package com.blood.donation.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Blood_bank")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BloodBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bloodBankId;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String contactNumber;

}
