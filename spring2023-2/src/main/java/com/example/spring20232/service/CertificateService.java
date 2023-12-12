package com.example.spring20232.service;


import com.example.spring20232.binding.dbDTO.CertificateImportDto;
import com.example.spring20232.binding.viewDTO.CertificateHateoasViewDto;
import com.example.spring20232.binding.viewDTO.CertificationViewDto;

import java.util.Collection;
import java.util.List;

public interface CertificateService {

    void saveNewCert(CertificateImportDto certificateImportDto);


    List<CertificationViewDto> findAllCertificationView();

    List<CertificateHateoasViewDto> getAllCertificateHateaosViewDtos();

    CertificateHateoasViewDto getSingleCertificateHateaosViewDtoByID(Long id);
}
