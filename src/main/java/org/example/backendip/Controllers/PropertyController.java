package org.example.backendip.Controllers;

import org.example.backendip.DTOs.CreatePropertyDTO;
import org.example.backendip.Models.Property;
import org.example.backendip.Services.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping()
    public ResponseEntity<List<Property>> getProperty() {
        List<Property> properties =  propertyService.findAll();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Property> createProperty(@RequestBody CreatePropertyDTO property) {
        Property createdProperty = propertyService.createProperty(property);
        return new ResponseEntity<>(createdProperty, HttpStatus.OK);
    }

}
