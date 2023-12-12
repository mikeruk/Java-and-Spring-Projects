package com.example.spring20232.service.impl;


import com.example.spring20232.binding.dbDTO.EducationImportDto;
import com.example.spring20232.binding.viewDTO.EducationHateoasViewDto;
import com.example.spring20232.binding.viewDTO.EducationViewDto;
import com.example.spring20232.binding.viewDTO.WorkExpHateoasViewDto;
import com.example.spring20232.model.entity.Education;
import com.example.spring20232.model.entity.WorkExp;
import com.example.spring20232.model.exceptions.EducationEntityNotFoundException;
import com.example.spring20232.model.exceptions.SkillEntityNotFoundException;
import com.example.spring20232.repository.EducationRepository;
import com.example.spring20232.service.EducationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EducationServiceImpl implements EducationService {


    private final EducationRepository educationRepository;

    private final ModelMapper modelMapper;


    public EducationServiceImpl(EducationRepository educationRepository, ModelMapper modelMapper) {
        this.educationRepository = educationRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addNewEducation(EducationImportDto educationImportDto) {
        Education educationEntity = modelMapper.map(educationImportDto, Education.class);
        educationRepository.save(educationEntity);
    }

    @Override
    public List<EducationViewDto> findAllEducationView() {

        List<EducationViewDto> educationViewDtoList = educationRepository.findAll()
                .stream()
                .map(educationEntity -> modelMapper.map(educationEntity, EducationViewDto.class))
                .collect(Collectors.toList());

        return educationViewDtoList;
    }



    @Override
    public List<EducationHateoasViewDto> getAllEducationHateaosViewDtos() {
        List<EducationHateoasViewDto> educationHateoasViewDtoList = educationRepository.findAll()
                .stream()
                .map(educationEntity -> modelMapper.map(educationEntity, EducationHateoasViewDto.class))
                .collect(Collectors.toList());
        return educationHateoasViewDtoList;
    }


    @Override
    public EducationHateoasViewDto getSingleEducationHateaosViewDtosByID(Long id) {
        Optional<Education> educationById = educationRepository.findAllById(id);

        if(educationById.isEmpty()){
            System.out.println();
            throw new EducationEntityNotFoundException(id);
        }

        EducationHateoasViewDto educationHateoasViewDto = modelMapper.map(educationById, EducationHateoasViewDto.class);
        return educationHateoasViewDto;
    }
}
