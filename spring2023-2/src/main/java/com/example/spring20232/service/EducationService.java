package com.example.spring20232.service;


import com.example.spring20232.binding.dbDTO.EducationImportDto;
import com.example.spring20232.binding.viewDTO.EducationHateoasViewDto;
import com.example.spring20232.binding.viewDTO.EducationViewDto;

import java.util.Collection;
import java.util.List;

public interface EducationService {

    void addNewEducation(EducationImportDto educationImportDto);

    List<EducationViewDto> findAllEducationView();

    List<EducationHateoasViewDto> getAllEducationHateaosViewDtos();

    EducationHateoasViewDto getSingleEducationHateaosViewDtosByID(Long id);
}
