package com.deepak.volunteer_info_system.Controller;

import com.deepak.volunteer_info_system.Config.jwtUtil;
import com.deepak.volunteer_info_system.Service.CustomUserDetailsService;
import com.deepak.volunteer_info_system.Service.UserService;
import com.deepak.volunteer_info_system.dto.LoginRequest;
import com.deepak.volunteer_info_system.dto.signupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final jwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody signupRequest signupRequest) {
        try {
            String response = userService.registerVolunteer(signupRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        final org.springframework.security.core.userdetails.UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(request.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails);

        // Return the token as a clean JSON response
        return ResponseEntity.ok(java.util.Map.of("token", jwt));
    }

}
