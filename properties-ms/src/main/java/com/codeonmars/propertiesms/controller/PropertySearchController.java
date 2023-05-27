package com.codeonmars.propertiesms.controller;

import com.codeonmars.propertiesms.model.property.dto.PropertyFilter;
import com.codeonmars.propertiesms.model.property.dto.PropertySimpleDto;
import com.codeonmars.propertiesms.service.PropertySearchService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/properties/search")
public record PropertySearchController(PropertySearchService propertySearchService) {

    @GetMapping
    public ResponseEntity<Page<PropertySimpleDto>> getPropertyList(@RequestBody PropertyFilter filter,
                                                                   @RequestParam(defaultValue = "0", value = "page", required = false) Integer page,
                                                                   @RequestParam(defaultValue = "20", value = "size", required = false) Integer size) {
        return new ResponseEntity<>(propertySearchService.searchProperty(filter, page, size), HttpStatus.OK);
    }
}
