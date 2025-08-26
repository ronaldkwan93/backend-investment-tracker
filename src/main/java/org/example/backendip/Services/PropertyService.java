package org.example.backendip.Services;

import org.example.backendip.DTOs.CreatePropertyDTO;
import org.example.backendip.Models.Property;
import org.example.backendip.Repositories.PropertyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
