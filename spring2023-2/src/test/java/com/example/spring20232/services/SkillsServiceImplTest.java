package com.example.spring20232.services;

import com.example.spring20232.binding.viewDTO.SkillsHateoasViewDto;
import com.example.spring20232.binding.viewDTO.SkillsViewDto;
import com.example.spring20232.model.entity.Skills;
import com.example.spring20232.model.exceptions.SkillEntityNotFoundException;
import com.example.spring20232.repository.SkillsRepository;
import com.example.spring20232.service.ApplicationUserDetailsService;
import com.example.spring20232.service.impl.SkillsServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SkillsServiceImplTest {


    private SkillsServiceImpl toTest;

    @Mock
    private SkillsRepository skillsRepository;

    @Mock
    private ModelMapper modelMapper;


    @BeforeEach
    void setup() {
        toTest = new SkillsServiceImpl(skillsRepository, modelMapper);
    }


    @Test
    void testFindAllSkills() {
        List<SkillsViewDto> allSkills = toTest.findAllSkills();
        Assertions.assertNotNull(allSkills);
    }

    @Test
    void testAllSkillsNotFound() {
        List<SkillsViewDto> allSkills = toTest.findAllSkills();
        Assertions.assertEquals(allSkills.size(), 0);
    }


    @Test
    void testGetAllSkillsHATEAOSViewDtos() {
        List<SkillsHateoasViewDto> skillsHateoasViewDtoList = toTest.findAllSkills()
                .stream()
                .map(skillsEntity -> modelMapper.map(skillsEntity, SkillsHateoasViewDto.class))
                .collect(Collectors.toList());
        Assertions.assertNotNull(skillsHateoasViewDtoList);
    }

    @Test
    void testAllSkillsHATEAOSViewDtosNotFound() {
        List<SkillsHateoasViewDto> skillsHateoasViewDtoList = toTest.findAllSkills()
                .stream()
                .map(skillsEntity -> modelMapper.map(skillsEntity, SkillsHateoasViewDto.class))
                .collect(Collectors.toList());
        Assertions.assertEquals(skillsHateoasViewDtoList.size(), 0);
    }

    @Test
    void testGetSkillsHateaosViewDtosByID() {
        Skills testStkillEntity = new Skills();
        when(skillsRepository.findById(1L))
                .thenReturn(Optional.of(testStkillEntity));
        toTest.getSingleWorkExpHateaosViewDtosByID(1L);
        Assertions.assertNotNull(testStkillEntity);
    }






}



