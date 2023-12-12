package com.example.spring20232.service.impl;


import com.example.spring20232.binding.dbDTO.RecommendationImportDto;
import com.example.spring20232.binding.dbDTO.UserRegisterBindingDto;
import com.example.spring20232.binding.viewDTO.RecommendationViewDto;
import com.example.spring20232.binding.viewDTO.UserEntityViewDto;
import com.example.spring20232.model.entity.Recommendation;
import com.example.spring20232.model.entity.UserEntity;
import com.example.spring20232.model.entity.UserRoleEntity;
import com.example.spring20232.model.enums.UserRoleEnum;
import com.example.spring20232.model.events.UserRegisteredEvent;
import com.example.spring20232.repository.RecommendationRepository;
import com.example.spring20232.repository.UserEntityRepository;
import com.example.spring20232.repository.UserRoleRepository;
import com.example.spring20232.service.UserEntityService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserEntityServiceImpl implements UserEntityService {


    private final UserEntityRepository userEntityRepository;

    private final ModelMapper modelMapper;

    private final UserRoleRepository userRoleRepository;

    private final RecommendationRepository recommendationRepository;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationEventPublisher applicationEventPublisher;

    private UserRegisteredEvent userRegisteredEvent;

    public UserEntityServiceImpl(UserEntityRepository userEntityRepository, ModelMapper modelMapper, UserRoleRepository userRoleRepository, RecommendationRepository recommendationRepository, PasswordEncoder passwordEncoder, ApplicationEventPublisher applicationEventPublisher) {
        this.userEntityRepository = userEntityRepository;
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
        this.recommendationRepository = recommendationRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @Override
    public boolean registerUser(UserRegisterBindingDto userRegisterBindingDto) {

        Optional<UserEntity> userEntityByEmail = userEntityRepository
                .findUserEntityByEmail(userRegisterBindingDto.getEmail());



        System.out.println();
        if (!userEntityByEmail.isEmpty()) {

            userRegisterBindingDto.setAlreadyRegistered(true);
            return true;
        }




        UserEntity userEntity = modelMapper.map(userRegisterBindingDto, UserEntity.class);

        Optional<UserRoleEntity> userRoleEntityByRole = userRoleRepository.findUserRoleEntityByRole(UserRoleEnum.MODERATOR);

        userEntity.addRole(userRoleEntityByRole.orElse(null));

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));



        userEntityRepository.save(userEntity);
        System.out.println();


        this.userRegisteredEvent = new UserRegisteredEvent(this, userEntity.getEmail());
        applicationEventPublisher.publishEvent(this.userRegisteredEvent);

        return false;
    }

    @Override
    public Long getLoggedUserId(String email) {

        Optional<UserEntity> userEntityByEmail = userEntityRepository.findUserEntityByEmail(email);

        return userEntityByEmail.orElse(null).getId();
    }

    @Override
    public UserEntity getUserById(Long id) {

        UserEntity userEntityById = userEntityRepository.findUserEntityById(id);


        return userEntityById;
    }



    @Transactional
    @Override
    public void addNewRecommendation(RecommendationImportDto recommendationImportDto, Long loggedUserId) {

        System.out.println();
        Recommendation newRecommendationEntityToAdd = modelMapper.map(recommendationImportDto, Recommendation.class);


        //UPDATE of list element in Entity
        //1. we save the element in its database
        recommendationRepository.save(newRecommendationEntityToAdd);

        //2. we query "reference" to the entity (this does not return the whole
        // object like repository.getById() does) but returns only reference to the stored entityByID
        UserEntity referenceToUserEntityById = userEntityRepository.getReferenceById(loggedUserId);

        //3. We add the new element in the list of elements in that entity
        referenceToUserEntityById.getRecommendations().add(newRecommendationEntityToAdd);

        //4. we save the change by referencing the entity directly, without saving a new entity in the DB.
        userEntityRepository.save(referenceToUserEntityById);

    }



    @Override
    public List<UserEntityViewDto> getAllUserEntities() {

        List<UserEntityViewDto> userEntityViewDtoList = userEntityRepository.findAll()
                .stream()
                .filter(userEntity -> userEntity.getRecommendations().size() > 0)
                .map(userEntity -> modelMapper.map(userEntity, UserEntityViewDto.class))
                .map(userEntityViewDto -> {

                    List<RecommendationViewDto> recommendationViewDtoList = userEntityViewDto.getRecommendations()
                            .stream()
                            .map(recommendation -> modelMapper.map(recommendation, RecommendationViewDto.class))
                            .collect(Collectors.toList());
                    userEntityViewDto.setRecommendationsView(recommendationViewDtoList);
                    return userEntityViewDto;
                })
                .collect(Collectors.toList());


        return userEntityViewDtoList;
    }






}
