package org.example.backendip.Services;

import org.example.backendip.DTOs.CreatePropertyDTO;
import org.example.backendip.DTOs.UpdatePropertyDTO;
import org.example.backendip.Models.Property;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Component
public class PropertyAiTools {

    private final PropertyService propertyService;

    public PropertyAiTools(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Tool(description = "List all investment properties currently tracked.")
    public List<Property> listProperties() {
        return propertyService.findAll();
    }

    @Tool(description = "Find properties whose address contains the given text (case-insensitive). Use this to resolve an address the user mentions to a property id.")
    public List<Property> findPropertiesByAddress(@ToolParam(description = "Full or partial street address to search for") String addressQuery) {
        return propertyService.findByAddress(addressQuery);
    }

    @Tool(description = "Create a new investment property record.")
    public String createProperty(
            @ToolParam(description = "Street address") String address,
            @ToolParam(description = "Suburb") String suburb,
            @ToolParam(description = "State") String state,
            @ToolParam(description = "Purchase price") BigDecimal purchasePrice,
            @ToolParam(description = "Weekly rent") BigDecimal weeklyRent) {
        if (address == null || address.isBlank() || suburb == null || suburb.isBlank()
                || state == null || state.isBlank() || purchasePrice == null || weeklyRent == null) {
            return "Could not create property: address, suburb, state, purchasePrice and weeklyRent are all required.";
        }
        CreatePropertyDTO dto = new CreatePropertyDTO();
        dto.setAddress(address);
        dto.setSuburb(suburb);
        dto.setState(state);
        dto.setPurchasePrice(purchasePrice);
        dto.setWeeklyRent(weeklyRent);
        Property created = propertyService.createProperty(dto);
        return "Created property id " + created.getId() + " at " + created.getAddress() + ".";
    }

    @Tool(description = "Update fields on an existing property. Only pass the fields that should change; leave others null.")
    public String updateProperty(
            @ToolParam(description = "Id of the property to update") Long propertyId,
            @ToolParam(description = "New street address, or null to leave unchanged", required = false) String address,
            @ToolParam(description = "New suburb, or null to leave unchanged", required = false) String suburb,
            @ToolParam(description = "New state, or null to leave unchanged", required = false) String state,
            @ToolParam(description = "New purchase price, or null to leave unchanged", required = false) BigDecimal purchasePrice,
            @ToolParam(description = "New weekly rent, or null to leave unchanged", required = false) BigDecimal weeklyRent) {
        UpdatePropertyDTO dto = new UpdatePropertyDTO();
        dto.setAddress(address);
        dto.setSuburb(suburb);
        dto.setState(state);
        dto.setPurchasePrice(purchasePrice);
        dto.setWeeklyRent(weeklyRent);
        try {
            Property updated = propertyService.updateProperty(propertyId, dto);
            return "Updated property id " + updated.getId() + " at " + updated.getAddress() + ".";
        } catch (NoSuchElementException ex) {
            return ex.getMessage();
        }
    }

    @Tool(description = "Estimate gross rental yield for a property, as a percentage of purchase price.")
    public Map<String, Object> estimatePropertyMetrics(@ToolParam(description = "Id of the property") Long propertyId) {
        Map<String, Object> result = new HashMap<>();
        Property property;
        try {
            property = propertyService.findById(propertyId);
        } catch (NoSuchElementException ex) {
            result.put("error", ex.getMessage());
            return result;
        }
        BigDecimal annualRent = property.getWeeklyRent().multiply(BigDecimal.valueOf(52));
        BigDecimal grossYieldPercent = annualRent
                .divide(property.getPurchasePrice(), 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
        result.put("address", property.getAddress());
        result.put("purchasePrice", property.getPurchasePrice());
        result.put("weeklyRent", property.getWeeklyRent());
        result.put("annualRent", annualRent);
        result.put("grossRentalYieldPercent", grossYieldPercent);
        return result;
    }
}
