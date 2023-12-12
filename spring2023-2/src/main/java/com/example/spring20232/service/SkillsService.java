package com.example.spring20232.service;


import com.example.spring20232.binding.dbDTO.SkillsImportDto;
import com.example.spring20232.binding.viewDTO.SkillsHateoasViewDto;
import com.example.spring20232.binding.viewDTO.SkillsViewDto;
import com.example.spring20232.model.entity.Skills;

import java.util.Collection;
import java.util.List;

public interface SkillsService {


    void addNewSkill(SkillsImportDto skillsImportDto);


    List<SkillsViewDto> findAllSkills();

    List<SkillsHateoasViewDto> getAllSkillsHateaosViewDtos();

    SkillsHateoasViewDto getSingleWorkExpHateaosViewDtosByID(Long id);
}
