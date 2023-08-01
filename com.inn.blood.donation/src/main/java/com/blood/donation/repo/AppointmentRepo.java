package com.blood.donation.repo;

import com.blood.donation.model.Appointment;
import com.blood.donation.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

    Appointment findByDonorDonorId(@Param("donorId") Integer donorID);

    Appointment findByCampaignCampaignId(@Param("campaignId") Integer campaignID);

    @Query(value = "SELECT a FROM Appointment a WHERE a.campaign.campaignId = ?1")
    List<Appointment> getAppointmentsByCampaignId(Integer campaignId);
}
