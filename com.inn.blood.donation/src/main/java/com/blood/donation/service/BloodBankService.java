package com.blood.donation.service;

import com.blood.donation.dto.CreateBloodBankRequestDTO;
import com.blood.donation.model.BloodBank;
import com.blood.donation.model.Donor;
import com.blood.donation.model.User;
import com.blood.donation.repo.BloodBankRepo;
import com.blood.donation.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodBankService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BloodBankRepo bloodBankRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createBloodBank(CreateBloodBankRequestDTO createBloodBankRequestDTO) {

        User user = new User();
        user.setFirstName(createBloodBankRequestDTO.getBankName());
        user.setUserName(createBloodBankRequestDTO.getUserName());
        user.setEmail(createBloodBankRequestDTO.getEmail());
        user.setPassword(getEncodedPassword(createBloodBankRequestDTO.getPassword()));
        user.setIsFirstLogin(false);
        user.setUserType("blood-bank");
        userRepo.save(user);

        BloodBank bloodBank = new BloodBank();
        bloodBank.setUser(user);
        bloodBank.setAddress(createBloodBankRequestDTO.getAddress());
        bloodBank.setDistrict(createBloodBankRequestDTO.getDistrict());
        bloodBank.setPostalCode(createBloodBankRequestDTO.getPostalCode());
        bloodBank.setContactNumber(createBloodBankRequestDTO.getContactNumber());
        bloodBankRepo.save(bloodBank);
    }

    public List<BloodBank> getAllBloodBanks() {
        return bloodBankRepo.findAll();
    }

    public BloodBank getBloodBankByUserId(Integer userID) {
        return bloodBankRepo.findByUserUserId(userID);
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
