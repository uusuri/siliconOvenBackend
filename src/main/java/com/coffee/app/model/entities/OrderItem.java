package com.coffee.app.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Drink drink;

    @ManyToOne
    private Order order;

    private Integer quantity;
    private BigDecimal totalPrice;

    public void recalculateTotalPrice() {
        BigDecimal price = drink.calculatePrice();
        this.totalPrice = price.multiply(BigDecimal.valueOf(quantity));
    }
}
