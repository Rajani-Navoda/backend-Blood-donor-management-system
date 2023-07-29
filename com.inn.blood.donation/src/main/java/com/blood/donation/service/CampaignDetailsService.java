package com.blood.donation.service;

import com.blood.donation.model.CampaignDetails;
import com.blood.donation.repo.CampaignDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignDetailsService {
    private final CampaignDetailsRepo campaignDetailsRepo;
    @Autowired
    public CampaignDetailsService(CampaignDetailsRepo campaignDetailsRepo) {
        this.campaignDetailsRepo = campaignDetailsRepo;
    }


}
