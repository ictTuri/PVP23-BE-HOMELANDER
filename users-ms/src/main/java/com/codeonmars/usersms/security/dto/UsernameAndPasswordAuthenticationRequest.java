package com.codeonmars.usersms.security.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsernameAndPasswordAuthenticationRequest {

    @NotBlank(message = "Please enter a valid email, phone number or NID!")
    private String credential;

    @NotBlank(message = "Please enter a password!")
    private String password;
}