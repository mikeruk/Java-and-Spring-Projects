package com.example.spring20232.service.impl;


import com.example.spring20232.binding.dbDTO.CertificateImportDto;
import com.example.spring20232.binding.viewDTO.CertificateHateoasViewDto;
import com.example.spring20232.binding.viewDTO.CertificationViewDto;
import com.example.spring20232.model.entity.Certification;
import com.example.spring20232.model.exceptions.CertificationEntityNotFoundException;
import com.example.spring20232.model.exceptions.SkillEntityNotFoundException;
import com.example.spring20232.repository.CertificationRepository;
import com.example.spring20232.service.CertificateService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CertificateServiceImpls implements CertificateService {


    private final CertificationRepository certificationRepository;

    private final ModelMapper modelMapper;

    public CertificateServiceImpls(CertificationRepository certificationRepository, ModelMapper modelMapper) {
        this.certificationRepository = certificationRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void saveNewCert(CertificateImportDto certificateImportDto) {


        Certification certificateEntity = modelMapper.map(certificateImportDto, Certification.class);

        certificationRepository.save(certificateEntity);


    }

    @Override
    public List<CertificationViewDto> findAllCertificationView() {

        List<CertificationViewDto> CertificationViewDtoList = certificationRepository
                .findAll()
                .stream()
                .map(certificationEntity -> modelMapper.map(certificationEntity, CertificationViewDto.class))
                .collect(Collectors.toList());

        return CertificationViewDtoList;
    }


    @Override
    public List<CertificateHateoasViewDto> getAllCertificateHateaosViewDtos() {

        List<CertificateHateoasViewDto> certificateHateoasViewDtoList = certificationRepository.findAll()
                .stream()
                .map(certificationEntity -> modelMapper.map(certificationEntity, CertificateHateoasViewDto.class))
                .collect(Collectors.toList());

        return certificateHateoasViewDtoList;


    }

    @Override
    public CertificateHateoasViewDto getSingleCertificateHateaosViewDtoByID(Long id) {

        Optional<Certification> certificateById = certificationRepository.findCertificateById(id);

        if(certificateById.isEmpty()){
            System.out.println();
            throw new CertificationEntityNotFoundException(id);
        }
        CertificateHateoasViewDto certificateHateoasViewDto = modelMapper.map(certificateById, CertificateHateoasViewDto.class);

        return certificateHateoasViewDto;
    }


}
