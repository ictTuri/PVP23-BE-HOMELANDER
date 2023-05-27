package com.codeonmars.usersms.controller;

import com.codeonmars.usersms.service.UserAuthenticationService;
import com.codeonmars.usersms.model.user.dto.CustomResponseDto;
import com.codeonmars.usersms.model.user.dto.UserRegisterDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/auth/register")
public class RegisterController {

    private final UserAuthenticationService userService;

    @PostMapping
    public ResponseEntity<CustomResponseDto> registerUser(@Valid @RequestBody UserRegisterDto request){
        return new ResponseEntity<>(userService.registerNewUser(request), HttpStatus.CREATED);
    }
}
