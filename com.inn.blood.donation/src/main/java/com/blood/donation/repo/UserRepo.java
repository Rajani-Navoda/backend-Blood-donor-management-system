package com.blood.donation.repo;

import com.blood.donation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUserName(@Param("userName") String userName);
}