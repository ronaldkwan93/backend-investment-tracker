package org.example.backendip.service;
import jakarta.validation.Valid;
import org.example.backendip.DTOs.CreatePropertyDTO;
import org.example.backendip.Models.Property;
import org.example.backendip.Repositories.PropertyRepository;
import org.example.backendip.Services.PropertyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {
    @Mock
    PropertyRepository propertyRepository;

    @InjectMocks
    PropertyService propertyService;


    @Test
    void AddPropertyShouldAddPropertySuccessfully() {
        CreatePropertyDTO propertyDto = new CreatePropertyDTO();
        propertyDto.setAddress("123 King Street");
        propertyDto.setState("NSW");
        propertyDto.setPurchasePrice(BigDecimal.valueOf(10000L));
        propertyDto.setWeeklyRent(BigDecimal.valueOf(100L));

        Property savedProperty = new Property();
        savedProperty.setAddress(propertyDto.getAddress());
        savedProperty.setState(propertyDto.getState());
        savedProperty.setPurchasePrice(propertyDto.getPurchasePrice());
        savedProperty.setWeeklyRent(propertyDto.getWeeklyRent());

        Mockito.when(propertyRepository.save(any(Property.class))).thenReturn(savedProperty);
        Property addedProperty = propertyService.createProperty(propertyDto);
        Assertions.assertEquals(savedProperty.getAddress(), addedProperty.getAddress());
        Assertions.assertEquals(savedProperty.getState(), addedProperty.getState());
        Assertions.assertEquals(savedProperty.getPurchasePrice(), addedProperty.getPurchasePrice());
        Assertions.assertEquals(savedProperty.getWeeklyRent(), addedProperty.getWeeklyRent());
    }
}
