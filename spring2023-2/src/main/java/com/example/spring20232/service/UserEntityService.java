package com.example.spring20232.service;


import com.example.spring20232.binding.dbDTO.RecommendationImportDto;
import com.example.spring20232.binding.dbDTO.UserRegisterBindingDto;
import com.example.spring20232.binding.viewDTO.UserEntityViewDto;
import com.example.spring20232.model.entity.UserEntity;

import java.util.List;

public interface UserEntityService {

    boolean registerUser(UserRegisterBindingDto userRegisterBindingDto);

    Long getLoggedUserId(String email);

    UserEntity getUserById(Long id);

    void addNewRecommendation(RecommendationImportDto recommendationImportDto, Long loggedUserId);

    List<UserEntityViewDto> getAllUserEntities();

}
