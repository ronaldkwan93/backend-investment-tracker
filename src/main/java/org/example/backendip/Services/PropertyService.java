package org.example.backendip.Services;

import org.example.backendip.DTOs.CreatePropertyDTO;
import org.example.backendip.DTOs.UpdatePropertyDTO;
import org.example.backendip.Models.Property;
import org.example.backendip.Repositories.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property createProperty(CreatePropertyDTO property) {
        Property p = new Property();
        p.setAddress(property.getAddress());
        p.setSuburb(property.getSuburb());
        p.setState(property.getState());
        p.setPurchasePrice(property.getPurchasePrice());
        p.setWeeklyRent(property.getWeeklyRent());
        propertyRepository.save(p);
        return p;
    }

    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    public Property findById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No property found with id " + id));
    }

    public List<Property> findByAddress(String address) {
        return propertyRepository.findByAddressContainingIgnoreCase(address);
    }

    public List<Property> findRecentlyAdded() {
        return propertyRepository.findTop5ByOrderByCreatedAtDesc();
    }

    public Property updateProperty(Long id, UpdatePropertyDTO dto) {
        Property p = findById(id);
        if (dto.getAddress() != null) p.setAddress(dto.getAddress());
        if (dto.getSuburb() != null) p.setSuburb(dto.getSuburb());
        if (dto.getState() != null) p.setState(dto.getState());
        if (dto.getPurchasePrice() != null) p.setPurchasePrice(dto.getPurchasePrice());
        if (dto.getWeeklyRent() != null) p.setWeeklyRent(dto.getWeeklyRent());
        propertyRepository.save(p);
        return p;
    }
}
