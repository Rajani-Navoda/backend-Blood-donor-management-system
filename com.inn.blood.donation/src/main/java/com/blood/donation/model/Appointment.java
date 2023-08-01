package com.blood.donation.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Appointment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;

    @ManyToOne()
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @ManyToOne()
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;
}
