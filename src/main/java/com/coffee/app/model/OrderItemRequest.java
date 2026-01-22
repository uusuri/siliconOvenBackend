package com.coffee.app.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderItemRequest {
    private Long drinkId;
    private int quantity;
    private List<String> selectedAddOns;

    public OrderItemRequest(Long drinkId, int quantity) {
        this.drinkId = drinkId;
        this.quantity = quantity;
    }
}
