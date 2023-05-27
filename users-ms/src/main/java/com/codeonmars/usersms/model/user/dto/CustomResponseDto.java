package com.codeonmars.usersms.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CustomResponseDto {

    private String message;
    private LocalDateTime time;
}
