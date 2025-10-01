package org.example.backendip.Controllers;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.example.backendip.DTOs.CreatePropertyDTO;
import org.example.backendip.DTOs.UpdatePropertyDTO;
import org.example.backendip.Models.Property;
import org.example.backendip.Services.PropertyService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/property")
@Tag(name = "Property", description = "Endpoints for managing Investment Properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping()
    @Operation(
            summary = "Retrieve all properties",
            description = "Returns a list of all properties available in the system"
    )
    @Tool(description = "Get all items from the database")
    public ResponseEntity<List<Property>> getProperty() {
        List<Property> properties =  propertyService.findAll();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Update an existing property",
            description = "Modifies the details of an existing property identified by its ID"
    )
    public ResponseEntity<Property> updateProperty(@PathVariable long id, @RequestBody UpdatePropertyDTO data ) throws Exception {
        Optional<Property> property = propertyService.findById(id);
        if(!property.isPresent()) {
            throw new Exception("Can't find property of requested id");
        }
        Property updatedProperty = propertyService.updateProperty(property,data);
        return new ResponseEntity<>(updatedProperty,HttpStatus.OK);
    }

    @PostMapping()
    @Operation(
            summary = "Create a new property",
            description = "Adds a new property record to the system using the details provided in the request body"
    )
    public ResponseEntity<Property> createProperty( @RequestBody @Valid CreatePropertyDTO property) {
        Property createdProperty = propertyService.createProperty(property);
        return new ResponseEntity<>(createdProperty, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get property by ID", description = "Returns property details for a given ID")
    public ResponseEntity<Property> getPropertyById(@PathVariable long id) {
        Optional<Property> foundProperty = propertyService.findById(id);

        if (foundProperty.isPresent()) {
            return new ResponseEntity<>(foundProperty.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/recent-added")
    @Operation(
            summary = "Retrieve three most recently created properties",
            description = "Provides details of the latest three properties created in the system"
    )    public ResponseEntity<List<Property>> getRecentAddedProperty() {
        List<Property> properties =  propertyService.findRecent();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }


}
