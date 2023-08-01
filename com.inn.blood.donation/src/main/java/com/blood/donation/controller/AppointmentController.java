package com.blood.donation.controller;


import com.blood.donation.constants.Constants;
import com.blood.donation.model.*;
import com.blood.donation.service.AppointmentService;
import com.blood.donation.service.CampaignService;
import com.blood.donation.service.DonorService;
import com.blood.donation.service.UserService;
import com.blood.donation.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/appointment")
public class AppointmentController {

    @Autowired
    UserService userService;
    @Autowired
    DonorService donorService;
    @Autowired
    CampaignService campaignService;
    @Autowired
    AppointmentService appointmentService;

    @PostMapping(path = "/createAppointment/{userName}")
    @PreAuthorize("hasRole('ROLE_donor')")
    public ResponseEntity<String> signup(@PathVariable("userName") String userName, @RequestBody Integer campaignId) {
        try {
            User user = userService.getUserByName(userName);
            Donor donor = donorService.getDonorByUserId(user.getUserId());
            Campaign campaign = campaignService.getCampaignByCampaignId(campaignId);
            appointmentService.createAppointment(donor, campaign);
            return Utils.getResponseEntity("Appointment made successfully", HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
