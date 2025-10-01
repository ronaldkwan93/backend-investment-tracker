package org.example.backendip.Repositories;

import org.example.backendip.Models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    @Query(value = "SELECT * FROM property ORDER BY created_at DESC LIMIT 3", nativeQuery = true)
    List<Property> findMostRecent();
}
