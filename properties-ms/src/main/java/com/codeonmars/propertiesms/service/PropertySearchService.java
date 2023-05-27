package com.codeonmars.propertiesms.service;

import com.codeonmars.propertiesms.model.property.PropertiesEntity;
import com.codeonmars.propertiesms.model.property.dto.PropertyFilter;
import com.codeonmars.propertiesms.model.property.dto.PropertySimpleDto;
import com.codeonmars.propertiesms.repository.PropertyRepository;
import com.github.dozermapper.core.Mapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.codeonmars.propertiesms.service.specifications.PropertiesSpecifications.hasOwner;
import static com.codeonmars.propertiesms.service.specifications.PropertiesSpecifications.isForRent;
import static com.codeonmars.propertiesms.service.specifications.PropertiesSpecifications.isForSale;
import static com.codeonmars.propertiesms.service.specifications.PropertiesSpecifications.isFurnished;
import static com.codeonmars.propertiesms.service.specifications.PropertiesSpecifications.isInCity;
import static com.codeonmars.propertiesms.service.specifications.PropertiesSpecifications.isInCountry;
import static com.codeonmars.propertiesms.service.specifications.PropertiesSpecifications.isInPriceRange;
import static com.codeonmars.propertiesms.service.specifications.PropertiesSpecifications.isInSizeRange;
import static com.codeonmars.propertiesms.service.specifications.PropertiesSpecifications.isInYearRange;
import static com.codeonmars.propertiesms.service.specifications.PropertiesSpecifications.isInZone;
import static com.codeonmars.propertiesms.service.specifications.PropertiesSpecifications.isOfTopology;
import static com.codeonmars.propertiesms.service.specifications.PropertiesSpecifications.isRented;
import static com.codeonmars.propertiesms.service.specifications.PropertiesSpecifications.isSold;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Transactional
public class PropertySearchService {

    private final PropertyRepository propertyRepository;
    private final Mapper dozer;

    public PropertySearchService(PropertyRepository propertyRepository, Mapper dozer) {
        this.propertyRepository = propertyRepository;
        this.dozer = dozer;
    }

    public Page<PropertySimpleDto> searchProperty(PropertyFilter filter, Integer page, Integer size) {
        var pageable = PageRequest.of(page, size);

        Specification<PropertiesEntity> propertiesSpecifications = where(hasOwner(filter.getOwner()))
                .and(isInCountry(filter.getCountry()))
                .and(isInCity(filter.getCity()))
                .and(isInZone(filter.getZone()))
                .and(isRented(filter.getRented()))
                .and(isSold(filter.getSold()))
                .and(isForSale(filter.getForSale()))
                .and(isForRent(filter.getForRent()))
                .and(isFurnished(filter.getFurnished()))
                .and(isOfTopology(filter.getTypology()))
                .and(isInPriceRange(filter.getPriceRange()))
                .and(isInSizeRange(filter.getSizeRange()))
                .and(isInYearRange(filter.getYearRange()));

        return propertyRepository.findAll(propertiesSpecifications, pageable).map(this::convertToSimpleDto);
    }



    /* SUPPORTING METHODS */

    private PropertySimpleDto convertToSimpleDto(PropertiesEntity propertiesEntity) {
        return dozer.map(propertiesEntity, PropertySimpleDto.class);
    }
}
