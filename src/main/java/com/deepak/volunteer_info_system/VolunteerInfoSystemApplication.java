package com.deepak.volunteer_info_system;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class VolunteerInfoSystemApplication {


    public static void main(String[] args) {
        SpringApplication.run(VolunteerInfoSystemApplication.class, args);
    }

}
