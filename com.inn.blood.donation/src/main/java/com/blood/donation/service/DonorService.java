package com.blood.donation.service;

import com.blood.donation.dto.DonorRegisterRequestDTO;
import com.blood.donation.dto.UpdateDonorRequestDTO;
import com.blood.donation.model.Donor;
import com.blood.donation.model.User;
import com.blood.donation.repo.DonorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonorService {

    @Autowired
    DonorRepo donorRepo;
    public void addDonor(User user, DonorRegisterRequestDTO donorRegisterRequestDTO) {
        Donor donor = new Donor();
        donor.setUser(user);
        donor.setFullName(donorRegisterRequestDTO.getFullName());
        donor.setNic(donorRegisterRequestDTO.getNic());
        donor.setDob(donorRegisterRequestDTO.getDob());
        donor.setGender(donorRegisterRequestDTO.getGender());
        donor.setAddress(donorRegisterRequestDTO.getAddress());
        donor.setCity(donorRegisterRequestDTO.getCity());
        donor.setPostalCode(donorRegisterRequestDTO.getPostalCode());
        donor.setContactHome(donorRegisterRequestDTO.getContactHome());
        donor.setContactMobile(donorRegisterRequestDTO.getContactMobile());
        donor.setBloodGroup(donorRegisterRequestDTO.getBloodGroup());
        donor.setWeight(donorRegisterRequestDTO.getWeight());
        donor.setHeight(donorRegisterRequestDTO.getHeight());
        donor.setBmi(donorRegisterRequestDTO.getBmi());
        donor.setSpecialConditions((donorRegisterRequestDTO.getSpecialConditions()));

        donorRepo.save(donor);
    }

    public void updateDonor(User user, UpdateDonorRequestDTO updateDonorRequestDTO) {

        Donor donor = donorRepo.findByUserUserId(user.getUserId());

        donor.setUser(user);
        donor.setFullName(updateDonorRequestDTO.getFullName());
        donor.setGender(updateDonorRequestDTO.getGender());
        donor.setAddress(updateDonorRequestDTO.getAddress());
        donor.setCity(updateDonorRequestDTO.getCity());
        donor.setPostalCode(updateDonorRequestDTO.getPostalCode());
        donor.setContactHome(updateDonorRequestDTO.getContactHome());
        donor.setContactMobile(updateDonorRequestDTO.getContactMobile());
        donor.setWeight(updateDonorRequestDTO.getWeight());
        donor.setHeight(updateDonorRequestDTO.getHeight());
        donor.setBmi(updateDonorRequestDTO.getBmi());
        donor.setSpecialConditions((updateDonorRequestDTO.getSpecialConditions()));
        donor.setNic(donor.getNic());
        donor.setDob(donor.getDob());
        donor.setBloodGroup(donor.getBloodGroup());

        donorRepo.save(donor);
    }



    public Donor getDonorByUserId(Integer userID) {
        return donorRepo.findByUserUserId(userID);
    }
}
