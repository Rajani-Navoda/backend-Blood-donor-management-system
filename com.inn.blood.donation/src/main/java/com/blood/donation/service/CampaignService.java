package com.blood.donation.service;

import com.blood.donation.dto.ScheduleCampaignRequestDTO;
import com.blood.donation.model.Campaign;
import com.blood.donation.model.Donor;
import com.blood.donation.model.Organizer;
import com.blood.donation.repo.CampaignRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    @Autowired
    CampaignRepo campaignRepo;

    public void addCampaign(Organizer organizer, ScheduleCampaignRequestDTO scheduleCampaignRequestDTO) {

        Campaign campaign = new Campaign();

        campaign.setOrganizer(organizer);
        campaign.setDate(scheduleCampaignRequestDTO.getDate());
        campaign.setStartTime(scheduleCampaignRequestDTO.getStartTime());
        campaign.setEndTime(scheduleCampaignRequestDTO.getEndTime());
        campaign.setLocation(scheduleCampaignRequestDTO.getLocation());
        campaign.setPurpose(scheduleCampaignRequestDTO.getPurpose());
        campaign.setBloodBank(scheduleCampaignRequestDTO.getBloodBank());
        campaign.setStatus("Pending");

        campaignRepo.save(campaign);

    }

    public void approveCampaign(Integer campaignId) {
        Campaign pendingCampaign = campaignRepo.findByCampaignId(campaignId);
        if (pendingCampaign.getStatus().equals("Pending")) {
            pendingCampaign.setStatus("Approved");
        }
        campaignRepo.save(pendingCampaign);
    }

    public List<Campaign> getPendingCampaigns(Integer bloodBankId) {
        return campaignRepo.getPendingCampaigns(bloodBankId);
    }

    public List<Campaign> getApprovedCampaigns() {
        return campaignRepo.getApprovedCampaigns();
    }

    public List<Campaign> getApprovedCampaignsByOrganizer(Integer organizerId) {
        return campaignRepo.getApprovedCampaignsByOrganizer(organizerId);
    }

    public Campaign getCampaignByCampaignId(Integer campaignId) {
        return campaignRepo.findByCampaignId(campaignId);
    }
}
