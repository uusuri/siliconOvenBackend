package com.silicon.app.repositories;

import com.silicon.app.models.entities.DrinkBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrinkBaseRepository extends JpaRepository<DrinkBase, Long> {
    DrinkBase findByName(String name);
    Optional<DrinkBase> findByIsDefaultTrue();
}
