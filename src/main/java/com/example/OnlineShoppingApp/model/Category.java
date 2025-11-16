package com.example.OnlineShoppingApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category name cannot be empty")
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;

    public int getProductCount() {
        return products != null ? products.size() : 0;
    }
}
