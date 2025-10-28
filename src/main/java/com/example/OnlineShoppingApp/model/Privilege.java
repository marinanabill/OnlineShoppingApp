package com.example.OnlineShoppingApp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "privileges")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Privilege {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name; // e.g., "PRODUCT_CREATE", "ORDER_VIEW_ALL"
}
