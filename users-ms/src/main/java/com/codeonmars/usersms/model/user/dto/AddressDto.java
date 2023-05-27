package com.codeonmars.usersms.model.user.dto;

import lombok.Data;

@Data
public class AddressDto {
    private Long id;
    private String country;
    private String city;
    private String addressOne;
    private String addressTwo;
    private String zipCode;
    private String street;
}
