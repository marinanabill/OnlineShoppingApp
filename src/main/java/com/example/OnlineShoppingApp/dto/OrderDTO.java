package com.example.OnlineShoppingApp.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private Long id;
    private String status;
    private double total;
    private LocalDateTime placedAt;
    private Long userId;
    private List<OrderItemDTO> items;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public LocalDateTime getPlacedAt() { return placedAt; }
    public void setPlacedAt(LocalDateTime placedAt) { this.placedAt = placedAt; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public List<OrderItemDTO> getItems() { return items; }
    public void setItems(List<OrderItemDTO> items) { this.items = items; }
}
