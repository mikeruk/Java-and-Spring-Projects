package com.example.spring20232.services;


import com.example.spring20232.binding.viewDTO.RecommendationViewDto;
import com.example.spring20232.model.entity.Recommendation;
import com.example.spring20232.repository.RecommendationRepository;
import com.example.spring20232.repository.UserRepository;
import com.example.spring20232.service.ApplicationUserDetailsService;
import com.example.spring20232.service.impl.RecommendationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class RecommendationServiceImplTest {


    private RecommendationServiceImpl toTest;

    @Mock
    private RecommendationRepository mockRecommendationRepository;


    @Mock
    private ModelMapper mockModelMapper;


    @BeforeEach
    void setup() {
        toTest = new RecommendationServiceImpl(mockRecommendationRepository, mockModelMapper);
    }


    @Test
    void testFindAllRecommendations() {
        List<Recommendation> recommendationList = mockRecommendationRepository.findAll();
        Assertions.assertNotNull(recommendationList.stream().findAny());
        recommendationList.stream()
                .map(recommendationEntity -> mockModelMapper.map(recommendationEntity, RecommendationViewDto.class))
                .collect(Collectors.toList());
    }





}
