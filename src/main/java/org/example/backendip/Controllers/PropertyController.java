package org.example.backendip.Controllers;

import jakarta.validation.Valid;
import org.example.backendip.DTOs.CreatePropertyDTO;
import org.example.backendip.DTOs.UpdatePropertyDTO;
import org.example.backendip.Models.Property;
import org.example.backendip.Services.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping()
    public ResponseEntity<List<Property>> getProperty() {
        List<Property> properties =  propertyService.findAll();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Property> createProperty( @RequestBody @Valid CreatePropertyDTO property) {
        Property createdProperty = propertyService.createProperty(property);
        return new ResponseEntity<>(createdProperty, HttpStatus.OK);
    }

    @GetMapping("/recent-added")
    public ResponseEntity<List<Property>> getRecentlyAdded() {
        return new ResponseEntity<>(propertyService.findRecentlyAdded(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        return new ResponseEntity<>(propertyService.findById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody UpdatePropertyDTO property) {
        return new ResponseEntity<>(propertyService.updateProperty(id, property), HttpStatus.OK);
    }

}
