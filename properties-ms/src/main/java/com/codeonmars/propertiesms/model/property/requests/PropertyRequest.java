package com.codeonmars.propertiesms.model.property.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequest {
    private String description;
    private String longDescription;
    private Double size;
    private String unit;
    private Double price;
    private Integer year;
    private Boolean forRent;
    private Boolean forSale;
}
