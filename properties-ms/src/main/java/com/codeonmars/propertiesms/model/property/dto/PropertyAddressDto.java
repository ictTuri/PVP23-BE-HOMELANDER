package com.codeonmars.propertiesms.model.property.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyAddressDto {
    private Long id;
    private String country;
    private String city;
    private String zone;
    private String address;
    private Integer floorNumber;
    private Integer doorNumber;
    private String street;
}
