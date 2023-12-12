package com.example.spring20232.service.impl;


import com.example.spring20232.binding.dbDTO.SkillsImportDto;
import com.example.spring20232.binding.viewDTO.SkillsHateoasViewDto;
import com.example.spring20232.binding.viewDTO.SkillsViewDto;

import com.example.spring20232.model.entity.Skills;
import com.example.spring20232.model.exceptions.SkillEntityNotFoundException;
import com.example.spring20232.repository.SkillsRepository;
import com.example.spring20232.service.SkillsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillsServiceImpl implements SkillsService {


    private final SkillsRepository skillsRepository;

    private final ModelMapper modelMapper;

    public SkillsServiceImpl(SkillsRepository skillsRepository, ModelMapper modelMapper) {
        this.skillsRepository = skillsRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addNewSkill(SkillsImportDto skillsImportDto) {

        Skills skillsEntity = modelMapper.map(skillsImportDto, Skills.class);

        skillsRepository.save(skillsEntity);

    }

    @Override
    public List<SkillsViewDto> findAllSkills() {
        List<SkillsViewDto> skillsViewDtoList = skillsRepository.findAll()
                .stream()
                .map(skillEntity -> modelMapper.map(skillEntity, SkillsViewDto.class))
                .collect(Collectors.toList());

        return skillsViewDtoList;
    }

    @Override
    public List<SkillsHateoasViewDto> getAllSkillsHateaosViewDtos() {
        List<SkillsHateoasViewDto> skillsHateoasViewDtoList = skillsRepository.findAll()
                .stream()
                .map(skillsEntity -> modelMapper.map(skillsEntity, SkillsHateoasViewDto.class))
                .collect(Collectors.toList());

        return skillsHateoasViewDtoList;
    }

    @Override
    public SkillsHateoasViewDto getSingleWorkExpHateaosViewDtosByID(Long id) {
        Optional<Skills> skillsById = skillsRepository.findById(id);
        if(skillsById.isEmpty()){
            System.out.println();
            throw new SkillEntityNotFoundException(id);
        }

        SkillsHateoasViewDto skillsHateoasViewDto = modelMapper.map(skillsById, SkillsHateoasViewDto.class);

        return skillsHateoasViewDto;
    }
}
