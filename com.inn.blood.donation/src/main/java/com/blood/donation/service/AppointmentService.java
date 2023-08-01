package com.blood.donation.service;

import com.blood.donation.model.Appointment;
import com.blood.donation.model.Campaign;
import com.blood.donation.model.Donor;
import com.blood.donation.repo.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepo appointmentRepo;

    public void createAppointment(Donor donor, Campaign campaign) {

        Appointment appointment = new Appointment();
        appointment.setDonor(donor);
        appointment.setCampaign(campaign);

        appointmentRepo.save(appointment);

    }

    public List<Donor> getAppointmentDonorsByCampaignId(Integer campaignId) {
        List<Donor> donors = new ArrayList<>();
        for (Appointment appointment : appointmentRepo.getAppointmentsByCampaignId(campaignId)){
            donors.add(appointment.getDonor());
        }
        return donors;
    }
}
