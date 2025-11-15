package com.example.OnlineShoppingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class OnlineShoppingAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineShoppingAppApplication.class, args);
    }
}
