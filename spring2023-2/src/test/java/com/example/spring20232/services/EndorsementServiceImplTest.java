package com.example.spring20232.services;


import com.example.spring20232.binding.dbDTO.EndorsementImportDto;
import com.example.spring20232.binding.viewDTO.EndorsementViewDto;
import com.example.spring20232.model.entity.Endorsement;
import com.example.spring20232.repository.EndorsementRepository;
import com.example.spring20232.repository.UserRepository;
import com.example.spring20232.service.ApplicationUserDetailsService;
import com.example.spring20232.service.impl.EndorsementServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class EndorsementServiceImplTest {

    private EndorsementServiceImpl toTest;



    @Mock
    private EndorsementRepository mockEndorsementRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @BeforeEach
    void setup() {
        toTest = new EndorsementServiceImpl(mockEndorsementRepository, mockModelMapper);
    }


    @Test
    void testAddNewEndorsement() {
        EndorsementImportDto endorsementImportDto = new EndorsementImportDto();
        Assertions.assertNotNull(endorsementImportDto);
        Endorsement endorsementEntity = mockModelMapper.map(endorsementImportDto, Endorsement.class);
        mockEndorsementRepository.save(endorsementEntity);
    }


    @Test
    void getAllEndorsements() {
        List<Endorsement> endorsementList = mockEndorsementRepository.findAll();
        Assertions.assertNotNull(endorsementList.stream().findAny());
        endorsementList.stream()
                .map(endorsementEntity -> mockModelMapper.map(endorsementEntity, EndorsementViewDto.class))
                .collect(Collectors.toList());
    }







}
