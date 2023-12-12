package com.example.spring20232.service;

import com.example.spring20232.binding.viewDTO.RecommendationViewDto;


import java.util.List;

public interface RecommendationService {


    List<RecommendationViewDto> findAllRecommendations();



}
