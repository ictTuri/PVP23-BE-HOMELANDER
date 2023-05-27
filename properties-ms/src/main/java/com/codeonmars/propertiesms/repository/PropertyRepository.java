package com.codeonmars.propertiesms.repository;

import com.codeonmars.propertiesms.model.property.PropertiesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<PropertiesEntity, Long>, JpaSpecificationExecutor<PropertiesEntity> {

    Page<PropertiesEntity> findAll(Specification<PropertiesEntity> predicates, Pageable pageable);

    Optional<PropertiesEntity> findByIdAndOwner_Email(Long propertyId, String loggedUserEmail);
}
