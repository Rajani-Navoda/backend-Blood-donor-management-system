package com.blood.donation.controller;

import com.blood.donation.constants.Constants;
import com.blood.donation.dto.OrganizerRegisterRequestDTO;
import com.blood.donation.dto.UpdateDonorRequestDTO;
import com.blood.donation.dto.UpdateOrganizerRequestDTO;
import com.blood.donation.model.Donor;
import com.blood.donation.model.Organizer;
import com.blood.donation.model.User;
import com.blood.donation.repo.OrganizerRepo;
import com.blood.donation.service.OrganizerService;
import com.blood.donation.service.UserService;
import com.blood.donation.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/organizer")
public class OrganizerController {
    @Autowired
    private OrganizerService organizerService;
    @Autowired
    private UserService userService;

    @GetMapping("/getOrganizerByUserName/{userName}")
    @PreAuthorize("hasRole('ROLE_organizer')")
    public ResponseEntity<Organizer> getOrganizerByUserName(@PathVariable String userName){
        try {
            User user = userService.getUserByName(userName);
            Organizer organizer = organizerService.getOrganizerByUserId(user.getUserId());
            return new ResponseEntity<>(organizer, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/addOrganizer/{userName}")
    @PreAuthorize("hasRole('ROLE_organizer')")
    public ResponseEntity<String> addOrganizer(@PathVariable("userName") String userName, @RequestBody OrganizerRegisterRequestDTO organizerRegisterRequestDTO){
        try {
            User user = userService.getUserByName(userName);
            userService.updateIsFirstLoginToFalse(user);
            organizerService.addOrganizer(user, organizerRegisterRequestDTO);
            return Utils.getResponseEntity("Organizer added successfully", HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/updateOrganizer/{userName}")
    @PreAuthorize("hasRole('ROLE_organizer')")
    public  ResponseEntity<String> updateOrganizer(@PathVariable("userName") String userName, @RequestBody UpdateOrganizerRequestDTO updateOrganizerRequestDTO) {
        try {
            User user = userService.getUserByName(userName);
            User updatedUser = userService.updateUserDetails(user, updateOrganizerRequestDTO.getFirstName(), updateOrganizerRequestDTO.getLastName(), updateOrganizerRequestDTO.getEmail());
            organizerService.updateOrganizer(updatedUser, updateOrganizerRequestDTO);
            return Utils.getResponseEntity("Donor updated successfully", HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
