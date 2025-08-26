package org.example.backendip.Repositories;

import org.example.backendip.Models.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
