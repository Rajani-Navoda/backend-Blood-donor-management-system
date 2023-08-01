package com.blood.donation.repo;

import com.blood.donation.model.BloodBank;
import com.blood.donation.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BloodBankRepo extends JpaRepository<BloodBank, Integer> {

    BloodBank findByUserUserId(@Param("userId") Integer userId);
}
