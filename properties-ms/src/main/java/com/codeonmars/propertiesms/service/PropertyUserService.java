package com.codeonmars.propertiesms.service;

import com.codeonmars.commonsms.security.UserContextHolder;
import com.codeonmars.propertiesms.model.property.OwnerEntity;
import com.codeonmars.propertiesms.model.property.PropertiesEntity;
import com.codeonmars.propertiesms.model.property.dto.PropertyDetailedDto;
import com.codeonmars.propertiesms.model.property.requests.PropertyRequest;
import com.codeonmars.propertiesms.model.property.requests.PropertyUpdateRequest;
import com.codeonmars.propertiesms.remote.UsersApi;
import com.codeonmars.propertiesms.repository.PropertyRepository;
import com.github.dozermapper.core.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static com.codeonmars.propertiesms.service.specifications.PropertiesSpecifications.hasOwner;
import static com.codeonmars.propertiesms.service.specifications.PropertiesSpecifications.hasTenants;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Transactional
public class PropertyUserService {

    private final PropertyRepository propertyRepository;
    private final Mapper dozer;
    private final UsersApi usersApi;

    public PropertyUserService(PropertyRepository propertyRepository, Mapper dozer, UsersApi usersApi) {
        this.propertyRepository = propertyRepository;
        this.dozer = dozer;
        this.usersApi = usersApi;
    }

    @Transactional(readOnly = true)
    public Page<PropertyDetailedDto> getOwnedProperties(Integer page, Integer size) {
        var pageable = PageRequest.of(page, size);
        Specification<PropertiesEntity> propertiesSpecifications = where(hasOwner(getLoggedUserUsername()));
        return propertyRepository.findAll(propertiesSpecifications, pageable).map(this::convertToDetailedDto);
    }

    @Transactional(readOnly = true)
    public Page<PropertyDetailedDto> getUsingProperties(Integer page, Integer size) {
        var pageable = PageRequest.of(page, size);
        Specification<PropertiesEntity> propertiesSpecifications = where(hasTenants(getLoggedUserUsername()));
        return propertyRepository.findAll(propertiesSpecifications, pageable).map(this::convertToDetailedDto);
    }


    public void createNewProperty(PropertyRequest request) {
        var property = dozer.map(request, PropertiesEntity.class);
        var owner = new OwnerEntity(null, getLoggedUserUsername(), getLoggedUserEmail(), Instant.now());
        property.initializeAddressAndInfo();
        property.setOwner(owner);
        propertyRepository.saveAndFlush(property);
        usersApi.increasePropertySlot();
    }


    public void updateProperty(Long propertyId, PropertyUpdateRequest request) {
        var property = propertyRepository.findByIdAndOwner_Email(propertyId, getLoggedUserEmail());
        if(property.isPresent()){
            var prop = property.get();
            dozer.map(request, prop);
            propertyRepository.saveAndFlush(prop);
        }
    }


    public void deleteProperty(Long propertyId) {
        var property = propertyRepository.findByIdAndOwner_Email(propertyId, getLoggedUserEmail());
        property.ifPresent(propertyRepository::delete);
        usersApi.decreasePropertySlot();
    }

    /* SUPPORTING METHODS */

    private String getLoggedUserUsername(){
        /* TODO: fix the throw to a caught exception*/
        var user = UserContextHolder.getContext().orElseThrow();
        return user.getUsername();
    }

    private String getLoggedUserEmail(){
        /* TODO: fix the throw to a caught exception*/
        var user = UserContextHolder.getContext().orElseThrow();
        return user.getEmail();
    }

    private PropertyDetailedDto convertToDetailedDto(PropertiesEntity propertiesEntity) {
        return dozer.map(propertiesEntity, PropertyDetailedDto.class);
    }
}
