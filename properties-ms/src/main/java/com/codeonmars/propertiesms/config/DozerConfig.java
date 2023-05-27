package com.codeonmars.propertiesms.config;

import com.codeonmars.propertiesms.model.property.PropertiesEntity;
import com.codeonmars.propertiesms.model.property.dto.PropertyDetailedDto;
import com.codeonmars.propertiesms.model.property.dto.PropertySimpleDto;
import com.codeonmars.propertiesms.model.property.requests.PropertyUpdateRequest;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.dozermapper.core.DozerBeanMapperBuilder.create;
import static com.github.dozermapper.core.loader.api.TypeMappingOptions.mapNull;
import static com.github.dozermapper.core.loader.api.TypeMappingOptions.oneWay;

@Configuration
public class DozerConfig {

    @Bean
    public Mapper beanMapper() {
        return create().withMappingBuilders(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(
                        type(PropertiesEntity.class),
                        type(PropertySimpleDto.class),
                        oneWay(),
                        mapNull(false))
                        .fields("additionalAttributes.images","images");
            }
        }).withMappingBuilders(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(
                        type(PropertiesEntity.class),
                        type(PropertyDetailedDto.class),
                        oneWay(),
                        mapNull(false))
                        .fields("owner.email", "currentOwner")
                        .fields("tenant.email","currentTenant");
            }
        }).withMappingBuilders(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(
                        type(PropertyUpdateRequest.class),
                        type(PropertiesEntity.class),
                        oneWay(),
                        mapNull(true))
                        .fields("attributes", "additionalAttributes")
                        .fields("address","propertyAddress");
            }
        }).build();
    }

}
