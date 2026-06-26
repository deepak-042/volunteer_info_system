package com.deepak.volunteer_info_system.Repository;

import com.deepak.volunteer_info_system.Entity.VolunteerProfile;
import com.deepak.volunteer_info_system.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolunteerProfileRepository extends JpaRepository<VolunteerProfile,Long> {
    List<VolunteerProfile> findByStatus(StatusType status);
}
