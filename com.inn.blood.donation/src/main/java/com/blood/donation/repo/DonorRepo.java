package com.blood.donation.repo;


import com.blood.donation.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepo extends JpaRepository<Donor, Long> {

}
