package com.blood.donation.controller;

import com.blood.donation.model.CampaignDetails;
import com.blood.donation.repo.CampaignDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class CampaignDetailsController {
    private final CampaignDetailsRepo campaignDetailsRepo;


    @Autowired
    public CampaignDetailsController(CampaignDetailsRepo campaignDetailsRepo) {
        this.campaignDetailsRepo = campaignDetailsRepo;
    }

    @PreAuthorize("hasRole('ROLE_donor')")
    @GetMapping("/getAllCampaignDetails")
    public ResponseEntity<List<CampaignDetails>> GetAllCampaignDetails() {
        try {
            List<CampaignDetails> campaignDetailsList = new ArrayList<>();
            campaignDetailsRepo.findAll().forEach(campaignDetailsList::add);

            if (campaignDetailsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(campaignDetailsList, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ROLE_donor')")
    @PostMapping("/AddCampaignDetails")
    public ResponseEntity<CampaignDetails> addCampaignDetails(@RequestBody CampaignDetails campaignDetails){
        CampaignDetails campaignDetailsObj = campaignDetailsRepo.save(campaignDetails);
        return new ResponseEntity<>(campaignDetailsObj, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_donor')")
    @PostMapping("/updateCampaignDetailsById/{campaign_ID}")
    public ResponseEntity<Object> updateCampaignDetailsById(@PathVariable Long campaign_ID, @RequestBody CampaignDetails newCampaignDetails) {
        Optional<CampaignDetails> oldCampaignDetailsData = campaignDetailsRepo.findById(campaign_ID);
        if (oldCampaignDetailsData.isPresent()) {

            CampaignDetails updatedCampaignDetailsData = oldCampaignDetailsData.get();
            updatedCampaignDetailsData.setOrganizer_name(newCampaignDetails.getOrganizer_name());
            updatedCampaignDetailsData.setLocation_address(newCampaignDetails.getLocation_address());
            updatedCampaignDetailsData.setLocation_URL(newCampaignDetails.getLocation_URL());
            updatedCampaignDetailsData.setDate(newCampaignDetails.getDate());
            updatedCampaignDetailsData.setStart_time(newCampaignDetails.getStart_time());
            updatedCampaignDetailsData.setEnd_time(newCampaignDetails.getEnd_time());
            updatedCampaignDetailsData.setEmail(newCampaignDetails.getEmail());
            updatedCampaignDetailsData.setContact_num(newCampaignDetails.getContact_num());
            updatedCampaignDetailsData.setPurpose(newCampaignDetails.getPurpose());

            CampaignDetails campaignDetailsObj = campaignDetailsRepo.save(updatedCampaignDetailsData);

            return new ResponseEntity<>(campaignDetailsObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/DeleteCampaignDetailById/{Campaign_ID}")
    public ResponseEntity<HttpStatus> deleteCampaignDetailsById(@PathVariable Long Campaign_ID){
        campaignDetailsRepo.deleteById(Campaign_ID);
        return new ResponseEntity<>(HttpStatus.OK);


    }



}
