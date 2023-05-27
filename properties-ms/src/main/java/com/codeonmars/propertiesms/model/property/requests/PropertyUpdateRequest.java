package com.codeonmars.propertiesms.model.property.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PropertyUpdateRequest extends PropertyRequest{
    private Boolean rented;
    private Boolean sold;
    private TenantUpdateRequest tenant;
    private AdditionalAttributesUpdateRequest attributes;
    private AddressUpdateRequest address;
}
