package com.codeonmars.propertiesms.controller;

import com.codeonmars.commonsms.aspects.CanAddProperties;
import com.codeonmars.propertiesms.model.property.dto.PropertyDetailedDto;
import com.codeonmars.propertiesms.model.property.requests.PropertyRequest;
import com.codeonmars.propertiesms.model.property.requests.PropertyUpdateRequest;
import com.codeonmars.propertiesms.service.PropertyUserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/properties/user")
public class PropertyUserController {

    private final PropertyUserService propertyUserService;

    public PropertyUserController(PropertyUserService propertyUserService) {
        this.propertyUserService = propertyUserService;
    }

    @GetMapping("/owned")
    @ResponseStatus(HttpStatus.OK)
    public Page<PropertyDetailedDto> getOwnedProperties(@RequestParam(defaultValue = "0", value = "page", required = false) Integer page,
                                                        @RequestParam(defaultValue = "20", value = "size", required = false) Integer size) {
        return propertyUserService.getOwnedProperties(page, size);
    }

    @GetMapping("/using")
    @ResponseStatus(HttpStatus.OK)
    public Page<PropertyDetailedDto> getUsingProperties(@RequestParam(defaultValue = "0", value = "page", required = false) Integer page,
                                                        @RequestParam(defaultValue = "20", value = "size", required = false) Integer size) {
        return propertyUserService.getUsingProperties(page, size);
    }

    @PostMapping
    @CanAddProperties
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProperty(@RequestBody PropertyRequest request) {
        propertyUserService.createNewProperty(request);
    }

    @PutMapping("/{property-id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProperty(@PathVariable(name = "property-id") Long propertyId,
                               @RequestBody PropertyUpdateRequest request) {
        propertyUserService.updateProperty(propertyId, request);
    }

    @DeleteMapping("/{property-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProperty(@PathVariable(name = "property-id") Long propertyId) {
        propertyUserService.deleteProperty(propertyId);
    }

}
