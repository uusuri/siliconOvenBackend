package com.coffee.app.repositiory;

import com.coffee.app.model.entities.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
    Drink findByName(String name);
}
