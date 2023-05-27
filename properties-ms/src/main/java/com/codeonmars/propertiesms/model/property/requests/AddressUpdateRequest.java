package com.codeonmars.propertiesms.model.property.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressUpdateRequest {
    private String country;
    private String city;
    private String zone;
    private String address;
    private Integer floorNumber;
    private Integer doorNumber;
    private String street;
}
