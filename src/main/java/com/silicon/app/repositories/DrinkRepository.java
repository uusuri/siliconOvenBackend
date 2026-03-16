package com.silicon.app.repositories;

import com.silicon.app.models.entities.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
    Drink findByName(String name);
}
