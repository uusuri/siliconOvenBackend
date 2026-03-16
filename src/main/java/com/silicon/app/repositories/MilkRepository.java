package com.silicon.app.repositories;

import com.silicon.app.models.entities.Milk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilkRepository extends JpaRepository<Milk, Long> {
    Milk findByName(String name);
}
