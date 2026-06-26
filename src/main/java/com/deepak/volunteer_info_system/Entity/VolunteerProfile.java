package com.deepak.volunteer_info_system.Entity;

import com.deepak.volunteer_info_system.StatusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "volunteer_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerProfile {
    @Id
    private Long id;

    @OneToOne
    @MapsId  // Shares the primary key with the User entity
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String phoneNumber;

    private String skills; // e.g., "Web Design, Tutoring, Event Planning"

    private String availability;

    @Enumerated(EnumType.STRING)
    private StatusType status = StatusType.PENDING;

    private LocalDate joinedDate = LocalDate.now();
}
