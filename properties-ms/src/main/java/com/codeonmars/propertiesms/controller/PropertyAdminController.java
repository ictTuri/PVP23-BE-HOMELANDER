package com.codeonmars.propertiesms.controller;

import com.codeonmars.commonsms.security.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/properties/admin")
public class PropertyAdminController {

    @GetMapping
    public ResponseEntity<List<String>> getProperties(){
        var context = UserContextHolder.getContext();
        log.info(String.valueOf(context));
        return new ResponseEntity<List<String>>(List.of("hello", "world"), HttpStatus.OK);
    }
}
