package com.example.spring20232.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
//когато трябва да мапнем ДАТА от DTO-обекта към entity обекта и базата данни
        Converter<String, LocalDate> toLocalDate = new Converter<String, LocalDate>() {

            @Override
            public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {
                return mappingContext.getSource() == null
                        ? LocalDate.now()
                        : LocalDate.parse(mappingContext.getSource(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
        };
        modelMapper.addConverter(toLocalDate);

        return modelMapper;
    }





}
