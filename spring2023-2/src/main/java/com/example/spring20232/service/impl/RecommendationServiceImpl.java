package com.example.spring20232.service.impl;

import com.example.spring20232.binding.viewDTO.RecommendationViewDto;
import com.example.spring20232.model.entity.Recommendation;
import com.example.spring20232.repository.RecommendationRepository;
import com.example.spring20232.service.RecommendationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    private final RecommendationRepository recommendationRepository;
    private final ModelMapper modelMapper;

    public RecommendationServiceImpl(RecommendationRepository recommendationRepository, ModelMapper modelMapper) {
        this.recommendationRepository = recommendationRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<RecommendationViewDto> findAllRecommendations() {

        List<RecommendationViewDto> recommendationViewDtoList = recommendationRepository.findAll()
                .stream()
                .map(recommendationEntity -> modelMapper.map(recommendationEntity, RecommendationViewDto.class))
                .collect(Collectors.toList());


        return recommendationViewDtoList;
    }


}
