package com.example.OnlineShoppingApp.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @Column(nullable = false)
    private Date orderDate = new Date();

    @Column(nullable = false)
    private Double totalPrice;
}
