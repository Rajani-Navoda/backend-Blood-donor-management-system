package com.blood.donation.repo;

import com.blood.donation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUserName(@Param("userName") String userName);
}