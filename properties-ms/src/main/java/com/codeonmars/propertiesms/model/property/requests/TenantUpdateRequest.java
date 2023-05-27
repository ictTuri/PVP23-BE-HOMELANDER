package com.codeonmars.propertiesms.model.property.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantUpdateRequest {
    private String email;
}
