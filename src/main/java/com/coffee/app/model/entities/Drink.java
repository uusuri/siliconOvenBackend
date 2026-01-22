package com.coffee.app.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "drinks")
@Getter
@Setter
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal basePrice;

    @ManyToOne
    private Coffee defaultCoffee;

    @ManyToOne
    private Milk defaultMilk;

    @ManyToMany
    private List<Syrup> defaultSyrups;

    @ManyToMany
    private List<Syrup> additionalSyrups;

    public BigDecimal calculatePrice() {
        BigDecimal price = this.basePrice;

        if (this.defaultCoffee != null) {
            price = price.add(BigDecimal.valueOf(this.defaultCoffee.getPrice()));
        }
        if (this.defaultMilk != null) {
            price = price.add(BigDecimal.valueOf(this.defaultMilk.getPrice()));
        }

        if (this.defaultSyrups != null && !this.defaultSyrups.isEmpty()) {
            for (Syrup syrup : this.defaultSyrups) {
                price = price.add(syrup.getPrice());
            }
        }

        if (this.additionalSyrups != null && !this.additionalSyrups.isEmpty()) {
            for (Syrup syrup : this.additionalSyrups) {
                price = price.add(syrup.getPrice());
            }
        }
        return price;
    }
}
