package com.example.spring20232.service;


import com.example.spring20232.binding.dbDTO.EndorsementImportDto;
import com.example.spring20232.binding.viewDTO.EndorsementViewDto;

import java.util.List;

public interface EndorsementService {


    void addNewEndorsement(EndorsementImportDto endorsementImportDto);

    List<EndorsementViewDto> getAllEndorsements();
}
