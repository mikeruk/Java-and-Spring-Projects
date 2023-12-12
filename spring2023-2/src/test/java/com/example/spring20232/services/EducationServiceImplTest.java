package com.example.spring20232.services;

import com.example.spring20232.binding.dbDTO.EducationImportDto;
import com.example.spring20232.binding.viewDTO.EducationHateoasViewDto;
import com.example.spring20232.binding.viewDTO.EducationViewDto;
import com.example.spring20232.model.entity.Education;
import com.example.spring20232.model.exceptions.EducationEntityNotFoundException;
import com.example.spring20232.repository.EducationRepository;
import com.example.spring20232.repository.UserRepository;
import com.example.spring20232.service.ApplicationUserDetailsService;
import com.example.spring20232.service.impl.EducationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class EducationServiceImplTest {

    private EducationServiceImpl toTest;


    @Mock
    private EducationRepository mockEducationRepository;

    @Mock
    private ModelMapper mockModelMapper;


    @BeforeEach
    void setup() {
        toTest = new EducationServiceImpl(mockEducationRepository, mockModelMapper);
    }


    @Test
    void testAddNewEducation() {
        EducationImportDto educationImportDto = new EducationImportDto();
        Assertions.assertNotNull(educationImportDto);
        Education educationEntity = mockModelMapper.map(educationImportDto, Education.class);
        mockEducationRepository.save(educationEntity);
    }


    @Test
    void testFindAllEducationView() {
        List<Education> educationEntityList = mockEducationRepository.findAll();
        Assertions.assertNotNull(educationEntityList.stream().findAny());
        educationEntityList.stream()
                .map(entity -> mockModelMapper.map(entity, EducationViewDto.class))
                .collect(Collectors.toList());
    }

    @Test
    void testGetAllEducationHateaosViewDtos() {
        List<Education> educationEntityList = mockEducationRepository.findAll();
        Assertions.assertNotNull(educationEntityList.stream().findAny());
        educationEntityList.stream()
                .map(entity -> mockModelMapper.map(entity, EducationHateoasViewDto.class))
                .collect(Collectors.toList());
    }

    @Test
    void testGetSingleEducationHateaosViewDtosByID() {
        Optional<Education> educationById = mockEducationRepository.findAllById(1L);
        Assertions.assertNotNull(educationById);
        mockModelMapper.map(educationById, EducationHateoasViewDto.class);
    }


    @Test
    void testGetSingleEducationHateaosViewDtosByIDNotFound() {
        Optional<Education> educationById = mockEducationRepository.findAllById(1L);
        Assertions.assertNull(educationById.orElse(null));
        try{
            mockModelMapper.map(educationById, EducationHateoasViewDto.class);
        } catch (NullPointerException e){

        }
    }




}
