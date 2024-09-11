package com.assignment.transactions.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for ModelMapper.
 * Provides a ModelMapper bean for mapping between DTOs and entities.
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Creates and configures a ModelMapper bean.
     *
     * @return a ModelMapper instance
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
