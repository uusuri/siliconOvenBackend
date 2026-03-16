package com.silicon.app.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class DrinkCreateRequest {
    @NotNull
    private String name;

    @NotNull
    private String image;

    @NotNull
    private BigDecimal basePrice;

    private Long drinkBaseId;

    private Long milkId;

    private List<Long> defaultSyrupIds;
}