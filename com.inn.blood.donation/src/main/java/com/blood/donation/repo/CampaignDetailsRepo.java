package com.blood.donation.repo;

import com.blood.donation.model.CampaignDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignDetailsRepo extends JpaRepository<CampaignDetails,Long> {

}
