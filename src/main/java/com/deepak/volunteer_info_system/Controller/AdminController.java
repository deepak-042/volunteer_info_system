package com.deepak.volunteer_info_system.Controller;

import com.deepak.volunteer_info_system.Entity.VolunteerProfile;
import com.deepak.volunteer_info_system.Repository.VolunteerProfileRepository;
import com.deepak.volunteer_info_system.StatusType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')") // Secures all endpoints in this class for Admins only
@RequiredArgsConstructor
public class AdminController {
    private final VolunteerProfileRepository volunteerProfileRepository;

    @GetMapping("/volunteers")
    public ResponseEntity<List<VolunteerProfile>> getAllVolunteers() {
        List<VolunteerProfile> volunteers = volunteerProfileRepository.findAll();
        return ResponseEntity.ok(volunteers);
    }

    @GetMapping("/volunteers/status/{status}")
    public ResponseEntity<List<VolunteerProfile>> getVolunteersByStatus(@PathVariable StatusType status) {
        List<VolunteerProfile> volunteers = volunteerProfileRepository.findByStatus(status);
        return ResponseEntity.ok(volunteers);
    }
    @PutMapping("/volunteers/{id}/status")
    public ResponseEntity<String> updateVolunteerStatus(
            @PathVariable Long id,
            @RequestParam StatusType status) {

        return volunteerProfileRepository.findById(id)
                .map(profile -> {
                    profile.setStatus(status);
                    volunteerProfileRepository.save(profile);
                    return ResponseEntity.ok("Volunteer status updated to " + status + " successfully.");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
