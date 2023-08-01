package com.blood.donation.service;

import com.blood.donation.dto.OrganizerRegisterRequestDTO;
import com.blood.donation.dto.UpdateOrganizerRequestDTO;
import com.blood.donation.model.Organizer;
import com.blood.donation.model.User;
import com.blood.donation.repo.OrganizerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService {

    @Autowired
    OrganizerRepo organizerRepo;

    public void addOrganizer(User user, OrganizerRegisterRequestDTO organizerRegisterRequestDTO) {

        Organizer organizer = new Organizer();

        organizer.setUser(user);
        organizer.setFullName(organizerRegisterRequestDTO.getFullName());
        organizer.setNic(organizerRegisterRequestDTO.getNic());
        organizer.setAddress(organizerRegisterRequestDTO.getAddress());
        organizer.setContactMobile(organizerRegisterRequestDTO.getContactMobile());
        organizer.setContactHome(organizerRegisterRequestDTO.getContactHome());

        organizerRepo.save(organizer);

    }

    public void updateOrganizer(User user, UpdateOrganizerRequestDTO updateOrganizerRequestDto) {

        Organizer organizer = organizerRepo.findByUserUserId(user.getUserId());

        organizer.setUser(user);
        organizer.setFullName(updateOrganizerRequestDto.getFullName());
        organizer.setAddress(updateOrganizerRequestDto.getAddress());
        organizer.setContactMobile(updateOrganizerRequestDto.getContactMobile());
        organizer.setContactHome(updateOrganizerRequestDto.getContactHome());
        organizer.setNic(organizer.getNic());

        organizerRepo.save(organizer);
    }

    public Organizer getOrganizerByUserId(Integer userID) {

        return organizerRepo.findByUserUserId(userID);
    }
}
