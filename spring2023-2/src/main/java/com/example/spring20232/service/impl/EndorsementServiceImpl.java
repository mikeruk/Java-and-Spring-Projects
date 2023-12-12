package com.example.spring20232.service.impl;

import com.example.spring20232.binding.dbDTO.EndorsementImportDto;
import com.example.spring20232.binding.viewDTO.EndorsementViewDto;
import com.example.spring20232.model.entity.Endorsement;
import com.example.spring20232.repository.EndorsementRepository;
import com.example.spring20232.service.EndorsementService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EndorsementServiceImpl implements EndorsementService {

    private final EndorsementRepository endorsementRepository;

    private final ModelMapper modelMapper;

    public EndorsementServiceImpl(EndorsementRepository endorsementRepository, ModelMapper modelMapper) {
        this.endorsementRepository = endorsementRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public void addNewEndorsement(EndorsementImportDto endorsementImportDto) {

        Endorsement endorsementEntity = modelMapper.map(endorsementImportDto, Endorsement.class);

        endorsementRepository.save(endorsementEntity);

    }

    @Override
    public List<EndorsementViewDto> getAllEndorsements() {
        List<EndorsementViewDto> endorsementViewDtoList = endorsementRepository.findAll()
                .stream()
                .map(endorsementEntity -> modelMapper.map(endorsementEntity, EndorsementViewDto.class))
                .collect(Collectors.toList());
        return endorsementViewDtoList;
    }
}
