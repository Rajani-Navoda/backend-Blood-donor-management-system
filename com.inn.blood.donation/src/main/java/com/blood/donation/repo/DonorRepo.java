package com.blood.donation.repo;


import com.blood.donation.model.Donor;
import com.blood.donation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface DonorRepo extends JpaRepository<Donor, Integer> {

    Donor findByUserUserId(@Param("userId") Integer userId);
}
