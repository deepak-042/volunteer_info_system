package com.deepak.volunteer_info_system.Service;

import com.deepak.volunteer_info_system.Entity.User;
import com.deepak.volunteer_info_system.Entity.VolunteerProfile;
import com.deepak.volunteer_info_system.Repository.UserRepository;
import com.deepak.volunteer_info_system.Repository.VolunteerProfileRepository;
import com.deepak.volunteer_info_system.RoleType;
import com.deepak.volunteer_info_system.StatusType;
import com.deepak.volunteer_info_system.dto.signupRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final VolunteerProfileRepository volunteerProfileRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String registerVolunteer(signupRequest request){
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Error: Email is already in use!");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(RoleType.ROLE_VOLUNTEER)); // Default role for public signups
        user.setEnabled(true);

        User savedUser = userRepository.save(user);
        VolunteerProfile profile = new VolunteerProfile();
        profile.setUser(savedUser); // Establishes the relationship
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setSkills(request.getSkills());
        profile.setAvailability(request.getAvailability());
        profile.setStatus(StatusType.PENDING);

        volunteerProfileRepository.save(profile);
        return "Volunteer registered successfully! Status is pending administrator approval.";
    }
}
