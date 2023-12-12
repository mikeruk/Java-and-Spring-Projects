package com.example.spring20232.web;


import com.example.spring20232.binding.viewDTO.UserEntityViewDto;
import com.example.spring20232.model.entity.UserEntity;
import com.example.spring20232.repository.UserEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller()
public class ProfileController {


    private final UserEntityRepository userEntityRepository;

    private final ModelMapper modelMapper;

    public ProfileController(UserEntityRepository userEntityRepository, ModelMapper modelMapper) {
        this.userEntityRepository = userEntityRepository;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/profile/{id}")
    public String home(@PathVariable Long id,
                       Model model) {

        Optional<UserEntity> userEntityById = userEntityRepository.findById(id);

        UserEntityViewDto userEntityViewDto = modelMapper.map(userEntityById, UserEntityViewDto.class);


        model.addAttribute(userEntityViewDto);
        return "profile";
    }

}
