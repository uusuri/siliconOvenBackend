package com.silicon.app.models.requests;

import com.silicon.app.models.entities.ProductType;
import lombok.Data;

import java.util.List;

@Data
public class OrderItemRequest {
    private ProductType productType;
    private Long id;
    private int quantity;
    private Long milkId;
    private List<Long> syrupIds;
}
