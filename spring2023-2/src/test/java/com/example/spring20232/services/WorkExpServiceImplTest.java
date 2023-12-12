package com.example.spring20232.services;


import com.example.spring20232.binding.dbDTO.WorkExpEditImportDto;
import com.example.spring20232.binding.dbDTO.WorkExpImportDto;
import com.example.spring20232.binding.viewDTO.WorkExpHateoasViewDto;
import com.example.spring20232.binding.viewDTO.WorkExpViewDto;
import com.example.spring20232.model.entity.WorkExp;
import com.example.spring20232.model.exceptions.WorkExpEntityNotFoundException;
import com.example.spring20232.repository.UserRepository;
import com.example.spring20232.repository.WorkExpRepository;
import com.example.spring20232.service.ApplicationUserDetailsService;
import com.example.spring20232.service.impl.WorkExpServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WorkExpServiceImplTest {


    private WorkExpServiceImpl toTest;


    @Mock
    private WorkExpRepository mockWorkExpRepository;

    @Mock
    private ModelMapper mockModelMapper;


    @BeforeEach
    void setup() {
        toTest = new WorkExpServiceImpl(mockWorkExpRepository, mockModelMapper);
    }


    @Test
    void testAddNewWorkExp() {
        WorkExpImportDto workExpImportDto = new WorkExpImportDto();
        Assertions.assertNotNull(workExpImportDto);
        WorkExp workExpEntity = mockModelMapper.map(workExpImportDto, WorkExp.class);
        mockWorkExpRepository.save(workExpEntity);
    }


    @Test
    void testFindAllWorkExpView() {
        List<WorkExp> workExpEntityList = mockWorkExpRepository.findAll();

        Optional<WorkExp> workExp = workExpEntityList.stream().findAny();
        Assertions.assertNotNull(workExp);

        List<WorkExpViewDto> workExpViewDtoList = workExpEntityList.stream()
                .map(workExpEntity -> mockModelMapper.map(workExpEntity, WorkExpViewDto.class))
                .collect(Collectors.toList());
    }


    @Test
    void testGetAllWorkExpHateaosViewDtos() {
        List<WorkExp> workExpList = mockWorkExpRepository.findAll();
        Optional<WorkExp> optionalWorkExp = workExpList.stream().findAny();
        Assertions.assertNotNull(optionalWorkExp);

        List<WorkExpHateoasViewDto> workExpHateoasViewDtoList = workExpList.stream()
                .map(workExpEntity -> mockModelMapper.map(workExpEntity, WorkExpHateoasViewDto.class))
                .collect(Collectors.toList());
    }


    @Test
    void testGetSingleWorkExpHateaosViewDtosByID() {
        Long id = 1L;
        Optional<WorkExp> workExpById = mockWorkExpRepository.findById(id);
        Assertions.assertEquals(workExpById.isEmpty(), true);

        if (workExpById.isEmpty()) {
            assertThrows(WorkExpEntityNotFoundException.class, () -> {
                toTest.getSingleWorkExpHateaosViewDtosByID(id);
            });
        }
    }










}
