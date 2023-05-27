package com.codeonmars.propertiesms.model.property.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YearRange {
    private Integer from;
    private Integer to;
}
