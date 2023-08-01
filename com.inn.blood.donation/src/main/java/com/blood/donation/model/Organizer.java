package com.blood.donation.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Organizer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Organizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer organizerId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String nic;

    @Column(nullable = false)
    private String contactMobile;

    private String contactHome;

    @Column(nullable = false)
    private String address;
}
