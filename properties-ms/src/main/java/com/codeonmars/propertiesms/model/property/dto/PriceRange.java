package com.codeonmars.propertiesms.model.property.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceRange {
    private Double from;
    private Double to;
}
