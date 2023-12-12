package com.example.spring20232.service;



import com.example.spring20232.binding.dbDTO.WorkExpEditImportDto;
import com.example.spring20232.binding.dbDTO.WorkExpImportDto;
import com.example.spring20232.binding.viewDTO.WorkExpHateoasViewDto;
import com.example.spring20232.binding.viewDTO.WorkExpViewDto;

import java.util.List;

public interface WorkExpService {


    void addNewWorkExp(WorkExpImportDto workExpImportDto);

    List<WorkExpViewDto> findAllWorkExpView();

    void editThisWorkExp(Long id, WorkExpEditImportDto workExpEditImportDto);

    List<WorkExpHateoasViewDto> getAllWorkExpHateaosViewDtos();

    WorkExpHateoasViewDto getSingleWorkExpHateaosViewDtosByID(Long id);

}
