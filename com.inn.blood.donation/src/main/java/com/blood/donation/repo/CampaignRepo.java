package com.blood.donation.repo;

import com.blood.donation.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CampaignRepo extends JpaRepository<Campaign, Integer> {

    @Query(value = "SELECT c FROM Campaign c WHERE c.status = 'Pending' AND c.bloodBank.bloodBankId = ?1")
    List<Campaign> getPendingCampaigns(Integer bloodBankId);

    @Query(value = "SELECT c FROM Campaign c WHERE c.status = 'Approved'")
    List<Campaign> getApprovedCampaigns();

    @Query(value = "SELECT c FROM Campaign c WHERE c.status = 'Approved' AND c.organizer.organizerId = ?1")
    List<Campaign> getApprovedCampaignsByOrganizer(Integer organizerId);

    Campaign findByCampaignId(Integer campaignId);
}
