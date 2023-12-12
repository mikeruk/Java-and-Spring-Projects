package com.example.spring20232.web;


import com.example.spring20232.binding.viewDTO.EndorsementViewDto;
import com.example.spring20232.binding.viewDTO.RecommendationViewDto;
import com.example.spring20232.binding.viewDTO.UserEntityViewDto;
import com.example.spring20232.service.EndorsementService;
import com.example.spring20232.service.UserEntityService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller()
public class AboutController {

    private final UserEntityService userEntityService;
    private String currentUserNameEmail;
    private final EndorsementService endorsementService;




    public AboutController(UserEntityService userEntityService, EndorsementService endorsementService) {
        this.userEntityService = userEntityService;
        this.endorsementService = endorsementService;
    }



    @ModelAttribute()
    public RecommendationViewDto recommendationViewDto(){
        return new RecommendationViewDto();
    }

    @GetMapping("/about")
    public String about(Model model) {


        final String currentUserNameEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(currentUserNameEmail);

        if (!currentUserNameEmail.equals("anonymousUser")) {
            Long loggedUserId = userEntityService.getLoggedUserId(currentUserNameEmail);
            System.out.println(loggedUserId);
            model.addAttribute("id", loggedUserId);
        }


        List<UserEntityViewDto> userEntityViewDtoList = userEntityService.getAllUserEntities();
        model.addAttribute("userEntityViewDtoList", userEntityViewDtoList);

        List<EndorsementViewDto> allEndorsementViewDtoList = endorsementService.getAllEndorsements();
        model.addAttribute("allEndorsementViewDtoList", allEndorsementViewDtoList);

        System.out.println();
        return "about";
    }




}
