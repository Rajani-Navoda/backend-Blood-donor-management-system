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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Org_ID;
    private String Org_name;
    private String Org_NIC;
    private String Org_contact;
    private String Org_email;
}
