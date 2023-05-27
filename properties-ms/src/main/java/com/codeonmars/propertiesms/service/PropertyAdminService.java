package com.codeonmars.propertiesms.service;

import com.codeonmars.propertiesms.repository.PropertyRepository;
import com.github.dozermapper.core.Mapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PropertyAdminService {

    private final PropertyRepository propertyRepository;
    private final Mapper dozer;

    public PropertyAdminService(PropertyRepository propertyRepository, Mapper dozer) {
        this.propertyRepository = propertyRepository;
        this.dozer = dozer;
    }



    /* SUPPORTING METHODS */

}
