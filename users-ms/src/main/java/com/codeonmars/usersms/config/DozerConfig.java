package com.codeonmars.usersms.config;

import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.dozermapper.core.DozerBeanMapperBuilder.create;

@Configuration
public class DozerConfig {

    @Bean
    public Mapper beanMapper() {
        return create().withMappingBuilders(new BeanMappingBuilder() {
            @Override
            protected void configure() {

            }
        }).build();
    }

}
