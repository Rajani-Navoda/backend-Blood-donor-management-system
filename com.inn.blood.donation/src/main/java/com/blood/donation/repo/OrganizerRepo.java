package com.blood.donation.repo;

import com.blood.donation.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepo extends JpaRepository<Organizer, Integer> {

    Organizer findByUserUserId(@Param("userId") Integer userId);

}
