package com.deepak.volunteer_info_system;

import com.deepak.volunteer_info_system.Entity.User;
import com.deepak.volunteer_info_system.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        String adminEmail = "admin@ngo.org";

        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            User admin = new User();
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("adminSecurePass123"));
            admin.setRoles(Set.of(RoleType.ROLE_ADMIN));
            admin.setEnabled(true);

            userRepository.save(admin);
            System.out.println(">> Permanent Admin account generated: admin@ngo.org / adminSecurePass123");
        }

    }
}
