package com.blood.donation.controller;

import com.blood.donation.constants.Constants;
import com.blood.donation.dto.CreateBloodBankRequestDTO;
import com.blood.donation.model.BloodBank;
import com.blood.donation.service.BloodBankService;
import com.blood.donation.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/blood-bank")
public class BloodBankController {

    @Autowired
    private BloodBankService bloodBankService;

    @GetMapping("/getAllBloodBanks")
    @PreAuthorize("hasRole('ROLE_organizer') || hasRole('ROLE_admin')")
    public ResponseEntity<List<BloodBank>> getAllBloodBanks(){
        try {
            List<BloodBank> allBloodBanks = bloodBankService.getAllBloodBanks();
            return new ResponseEntity<>(allBloodBanks, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createBloodBank")
    @PreAuthorize("hasRole('ROLE_admin')")
    public ResponseEntity<String> addDonor(@RequestBody CreateBloodBankRequestDTO createBloodBankRequestDTO) {
        try {
            bloodBankService.createBloodBank(createBloodBankRequestDTO);
            return Utils.getResponseEntity("Blood Bank created successfully", HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
