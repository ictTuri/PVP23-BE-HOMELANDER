package com.codeonmars.usersms.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSummaryDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean superuser;
    private TenantsSlotsDto tenantsSlots;
    private PropertiesSlotsDto propertiesSlots;
    private AddressDto address;
}
