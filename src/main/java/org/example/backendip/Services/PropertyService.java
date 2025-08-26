package org.example.backendip.Services;

import org.example.backendip.DTOs.CreatePropertyDTO;
import org.example.backendip.Models.Property;
import org.example.backendip.Repositories.PropertyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property createProperty(CreatePropertyDTO property) {
        Property p = new Property();
        p.setName(property.getPropertyName());
        p.setValue(property.getPropertyValue());
        propertyRepository.save(p);
        return p;
    }

    public List<Property> findAll() {
        return propertyRepository.findAll();
    }
}
