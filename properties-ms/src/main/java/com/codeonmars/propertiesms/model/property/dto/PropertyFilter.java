package com.codeonmars.propertiesms.model.property.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyFilter {
    private String owner;
    private Boolean forSale;
    private Boolean forRent;
    private Boolean rented;
    private Boolean sold;
    private SizeRange sizeRange;
    private String country;
    private String city;
    private String zone;
    private String typology;
    private Boolean furnished;
    private PriceRange priceRange;
    private YearRange yearRange;
}
