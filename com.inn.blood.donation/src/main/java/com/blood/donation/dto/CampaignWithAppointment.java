package com.blood.donation.dto;

import com.blood.donation.model.Appointment;
import com.blood.donation.model.Campaign;
import com.blood.donation.model.Donor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignWithAppointment {

    private Campaign campaign;
    private List<Donor> donors;
}
