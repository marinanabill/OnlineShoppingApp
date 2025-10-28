package com.example.OnlineShoppingApp.config;

import com.example.OnlineShoppingApp.model.*;
import com.example.OnlineShoppingApp.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepo;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepository roleRepo,
                           UserRepository userRepo,
                           PasswordEncoder passwordEncoder) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        List<String> roles = List.of("ROLE_ADMIN", "ROLE_CUSTOMER", "ROLE_SELLER", "ROLE_SUPPORT", "ROLE_DELIVERY");
        roles.forEach(r -> roleRepo.findByName(r).orElseGet(
                () -> roleRepo.save(Role.builder().name(r).build())
        ));

        String adminEmail = "admin@onlineshop.local";

        userRepo.findByEmail(adminEmail).orElseGet(() -> {
            Role adminRole = roleRepo.findByName("ROLE_ADMIN").get();

            User admin = User.builder()
                    .name("System Admin")
                    .email(adminEmail)
                    .password(passwordEncoder.encode("admin123"))
                    .status(User.Status.ACTIVE)
                    .roles(Set.of(adminRole))
                    .build();

            userRepo.save(admin);
            System.out.println("✅ Admin seeded: " + adminEmail + " / admin123");
            return admin;
        });
    }
}
