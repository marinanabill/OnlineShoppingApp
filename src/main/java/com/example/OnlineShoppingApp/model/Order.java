package com.example.OnlineShoppingApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull
    @Column(nullable = false)
    private Date orderDate = new Date();

    @NotNull
    @PositiveOrZero(message = "Total price cannot be negative")
    @Column(nullable = false)
    private Double totalPrice = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    public enum OrderStatus {
        PENDING,
        COMPLETED,
        CANCELLED
    }

    public void calculateTotalPrice() {
        if (items != null) {
            this.totalPrice = items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        } else {
            this.totalPrice = 0.0;
        }
    }
}
