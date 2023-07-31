package com.blood.donation.dto;

import com.blood.donation.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBloodBankRequestDTO {

    private String bankName;
    private String address;
    private String district;
    private String postalCode;
    private String contactNumber;
    private String email;
    private String userName;
    private String password;
}
