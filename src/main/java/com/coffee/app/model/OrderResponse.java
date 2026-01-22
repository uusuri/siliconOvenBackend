package com.coffee.app.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponse {
    private Long id;
    private String status;
    private LocalDateTime date;

    public OrderResponse(Long id, String status, LocalDateTime date) {
        this.id = id;
        this.status = status;
        this.date = date;
    }
}
