package com.silicon.app.repositories;

import com.silicon.app.models.entities.Syrup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyrupRepository extends JpaRepository<Syrup, Long> {
}
