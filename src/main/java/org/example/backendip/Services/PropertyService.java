package org.example.backendip.Services;

import jakarta.validation.Valid;
import org.example.backendip.DTOs.CreatePropertyDTO;
import org.example.backendip.DTOs.UpdatePropertyDTO;
import org.example.backendip.Models.Property;
import org.example.backendip.Repositories.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property createProperty(@Valid CreatePropertyDTO property) {
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

    public Optional<Property> findById(long id) {
        return propertyRepository.findById(id);

    }

    public Property updateProperty(Optional<Property> property, UpdatePropertyDTO data) {
        property.ifPresent(p -> p.setAddress(data.getAddress()));
        property.ifPresent(p -> p.setSuburb(data.getSuburb()));
        property.ifPresent(p -> p.setState(data.getState()));
        property.ifPresent(p -> p.setPurchasePrice(data.getPurchasePrice()));
        property.ifPresent(p -> p.setWeeklyRent(data.getWeeklyRent()));
        return propertyRepository.save(property.get());
    }

    public List<Property> findRecent() {
        return propertyRepository.findMostRecent();
    }
}
