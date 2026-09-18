package org.example.backendip.Repositories;

import org.example.backendip.Models.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByAddressContainingIgnoreCase(String address);
    List<Property> findTop5ByOrderByUpdatedAtDesc();
}
