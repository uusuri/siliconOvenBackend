package com.silicon.app.repositories;

import com.silicon.app.models.entities.AllergenType;
import com.silicon.app.models.entities.Bakery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BakeryRepository extends JpaRepository<Bakery, Long> {
//    @Query("SELECT b FROM Bakery b WHERE NOT b.allergens IN (:allergens)")
//    List<Bakery> findAllWithoutAllergens(List<AllergenType> allergens);
}
