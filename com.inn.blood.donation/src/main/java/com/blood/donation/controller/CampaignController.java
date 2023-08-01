package com.blood.donation.controller;

import com.blood.donation.constants.Constants;
import com.blood.donation.dto.CampaignWithAppointment;
import com.blood.donation.dto.ScheduleCampaignRequestDTO;
import com.blood.donation.model.*;
import com.blood.donation.service.*;
import com.blood.donation.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/campaign")
public class CampaignController {

    @Autowired
    private UserService userService;
    @Autowired
    private DonorService donorService;
    @Autowired
    private OrganizerService organizerService;
    @Autowired
    private CampaignService campaignService;
    @Autowired
    private BloodBankService bloodBankService;
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/addCampaign/{userName}")
    @PreAuthorize("hasRole('ROLE_organizer')")
    public ResponseEntity<String> addCampaign(@PathVariable("userName") String userName, @RequestBody ScheduleCampaignRequestDTO scheduleCampaignRequestDTO){
        try {
            User user = userService.getUserByName(userName);
            Organizer organizer = organizerService.getOrganizerByUserId(user.getUserId());
            campaignService.addCampaign(organizer, scheduleCampaignRequestDTO);
            return Utils.getResponseEntity("Campaign added successfully", HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return Utils.getResponseEntity(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getCampaignsByOrganizer/{userName}")
    @PreAuthorize("hasRole('ROLE_organizer')")
    public ResponseEntity<List<CampaignWithAppointment>> getCampaignsByOrganizer(@PathVariable String userName){
        try {
            List<CampaignWithAppointment> campaignWithAppointmentList = new ArrayList<>();
            User user = userService.getUserByName(userName);
            Organizer organizer = organizerService.getOrganizerByUserId(user.getUserId());
            List<Campaign> campaigns = campaignService.getApprovedCampaignsByOrganizer(organizer.getOrganizerId());
            for (Campaign campaign : campaigns) {
                CampaignWithAppointment campaignWithAppointment = new CampaignWithAppointment();
                List<Donor> donors = appointmentService.getAppointmentDonorsByCampaignId(campaign.getCampaignId());
                campaignWithAppointment.setCampaign(campaign);
                campaignWithAppointment.setDonors(donors);
                campaignWithAppointmentList.add(campaignWithAppointment);
            }
            return new ResponseEntity<>(campaignWithAppointmentList, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/approveCampaign")
    @PreAuthorize("hasRole('ROLE_blood-bank')")
    public ResponseEntity<String> approveCampaign(@RequestBody Integer campaignId) {
        try {
            campaignService.approveCampaign(campaignId);
            return Utils.getResponseEntity("Campaign approved successfully", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getPendingCampaigns/{userName}")
    @PreAuthorize("hasRole('ROLE_blood-bank')")
    public ResponseEntity<List<Campaign>> getPendingCampaigns(@PathVariable String userName){
        try {
            User user = userService.getUserByName(userName);
            BloodBank bloodBank = bloodBankService.getBloodBankByUserId(user.getUserId());
            List<Campaign> campaigns = campaignService.getPendingCampaigns(bloodBank.getBloodBankId());
            return new ResponseEntity<>(campaigns, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getNotAppointedCampaigns/{userName}")
    @PreAuthorize("hasRole('ROLE_donor')")
    public ResponseEntity<List<Campaign>> getNotAppointedCampaigns(@PathVariable("userName") String userName){
        try {
            List<Campaign> notAppointedCampaigns = new ArrayList<>();

            User user = userService.getUserByName(userName);
            Donor donor = donorService.getDonorByUserId(user.getUserId());

            List<Campaign> campaigns = campaignService.getApprovedCampaigns();
            for (Campaign campaign : campaigns) {
                if (!appointmentService.getAppointmentDonorsByCampaignId(campaign.getCampaignId()).contains(donor)) {
                    notAppointedCampaigns.add(campaign);
                }
            }
            return new ResponseEntity<>(notAppointedCampaigns, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
