package com.codeonmars.propertiesms.model.property.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDetailedDto {
    private Long id;
    private String description;
    private String longDescription;
    private Double size;
    private String unit;
    private Double price;
    private Integer year;
    private Boolean forRent;
    private Boolean forSale;
    private Boolean rented;
    private Boolean sold;
    private Set<String> tenants;
    private AdditionalAttributesDto additionalAttributes;
    private PropertyAddressDto propertyAddress;
    private String currentOwner;
    private String currentTenant;
}
