package com.example.OnlineShoppingApp.config;

import com.example.OnlineShoppingApp.model.Role;
import com.example.OnlineShoppingApp.repository.RoleRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RolesSeeder {
    @Bean
    public ApplicationRunner seedRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName("ROLE_USER").isEmpty()) {
                roleRepository.save(new Role("ROLE_USER"));
            }
            if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
                roleRepository.save(new Role("ROLE_ADMIN"));
            }
        };
    }
}
