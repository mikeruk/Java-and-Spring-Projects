package com.example.spring20232.service.impl;


import com.example.spring20232.binding.dbDTO.WorkExpEditImportDto;
import com.example.spring20232.binding.dbDTO.WorkExpImportDto;
import com.example.spring20232.binding.viewDTO.WorkExpHateoasViewDto;
import com.example.spring20232.binding.viewDTO.WorkExpViewDto;
import com.example.spring20232.model.entity.WorkExp;
import com.example.spring20232.model.exceptions.WorkExpEntityNotFoundException;
import com.example.spring20232.repository.WorkExpRepository;
import com.example.spring20232.service.WorkExpService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkExpServiceImpl implements WorkExpService {


    private final WorkExpRepository workExpRepository;

    private final ModelMapper modelMapper;


    public WorkExpServiceImpl(WorkExpRepository workExpRepository, ModelMapper modelMapper) {
        this.workExpRepository = workExpRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addNewWorkExp(WorkExpImportDto workExpImportDto) {
        WorkExp workExpEntity = modelMapper.map(workExpImportDto, WorkExp.class);
        workExpRepository.save(workExpEntity);
    }

    @Override
    public List<WorkExpViewDto> findAllWorkExpView() {

        List<WorkExpViewDto> WorkExpViewDtoList = workExpRepository.findAll()
                .stream()
                .map(workExpEntity -> modelMapper.map(workExpEntity, WorkExpViewDto.class))
                .collect(Collectors.toList());


        return WorkExpViewDtoList;
    }


    @Transactional
    @Override
    public void editThisWorkExp(Long id, WorkExpEditImportDto workExpEditImportDto) {

        String employerName = workExpEditImportDto.getEmployerName();
        String jobTitle = workExpEditImportDto.getJobTitle();
        String jobDescription = workExpEditImportDto.getJobDescription();
        String responsibilities = workExpEditImportDto.getResponsibilities();
        LocalDate startDate = workExpEditImportDto.getStartDate();
        LocalDate endDate = workExpEditImportDto.getEndDate();
        boolean stillPresent = workExpEditImportDto.isStillPresent();

        System.out.println();
        workExpRepository.updateEntity(id, employerName, jobTitle, jobDescription, responsibilities, startDate, endDate, stillPresent);

    }

    @Override
    public List<WorkExpHateoasViewDto> getAllWorkExpHateaosViewDtos() {
        List<WorkExpHateoasViewDto> WorkExpHateoasViewDtoList = workExpRepository.findAll()
                .stream()
                .map(workExpentity -> modelMapper.map(workExpentity, WorkExpHateoasViewDto.class))
                .collect(Collectors.toList());
        return WorkExpHateoasViewDtoList;
    }

    @Override
    public WorkExpHateoasViewDto getSingleWorkExpHateaosViewDtosByID(Long id) {
        Optional<WorkExp> workExpById = workExpRepository.findAllById(id);

        if (workExpById.isEmpty()) {
            throw new WorkExpEntityNotFoundException(id);
        }

        WorkExpHateoasViewDto workExpHateoasViewDto = modelMapper.map(workExpById, WorkExpHateoasViewDto.class);
        return workExpHateoasViewDto;
    }


}
