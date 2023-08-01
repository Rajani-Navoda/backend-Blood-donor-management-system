package com.blood.donation.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Campaign")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer campaignId;

    @ManyToOne()
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @ManyToOne()
    @JoinColumn(name = "blood_bank_id")
    private BloodBank bloodBank;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String purpose;

    @Column(nullable = false)
    private String status;
}
