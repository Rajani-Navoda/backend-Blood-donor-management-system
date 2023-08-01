package com.blood.donation.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Campaign_Details")

public class CampaignDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long campaign_ID;
    private String organizer_name;
    private String location_address;
    private String location_URL;
    private String date;
    private String start_time;
    private String end_time;
    private String email;
    private String contact_num;
    private String purpose;

}
