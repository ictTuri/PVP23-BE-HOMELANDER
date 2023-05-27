package com.codeonmars.propertiesms.model.property.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertySimpleDto {
    private Long id;
    private Set<UUID> images;
    private String description;
    private Double size;
    private String unit;
    private Double price;
}
