package com.coffee.app.repositiory;

import com.coffee.app.model.entities.Syrup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyrupRepository extends JpaRepository<Syrup, Long> {
}
