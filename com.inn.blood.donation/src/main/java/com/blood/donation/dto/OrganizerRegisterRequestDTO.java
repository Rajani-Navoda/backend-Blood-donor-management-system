package com.blood.donation.dto;

import com.blood.donation.model.BloodBank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizerRegisterRequestDTO {

    private String fullName;
    private String nic;
    private String contactMobile;
    private String contactHome;
    private String address;
    private BloodBank bloodBank;
}
