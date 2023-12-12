package com.example.spring20232.web;

import com.example.spring20232.binding.dbDTO.RecommendationImportDto;
import com.example.spring20232.model.entity.UserEntity;
import com.example.spring20232.repository.UserEntityRepository;
import com.example.spring20232.service.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RecommendationController {


    private String currentUserNameEmail;
    private final UserEntityService userEntityService;
    private final UserEntityRepository userEntityRepository;

    public RecommendationController(UserEntityService userEntityService,
                                    UserEntityRepository userEntityRepository) {
        this.userEntityService = userEntityService;
        this.userEntityRepository = userEntityRepository;
    }



    @GetMapping("/add-recommendation")
    public String recommend(Model model){

        currentUserNameEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!currentUserNameEmail.equals("anonymousUser")) {
            Long loggedUserId = userEntityService.getLoggedUserId(currentUserNameEmail);
            model.addAttribute("id", loggedUserId);
        }

        return "add-recommendation";
    }


    @ModelAttribute
    public RecommendationImportDto recommendationImportDto(){
        return new RecommendationImportDto();
    }

    @PostMapping("/add-recommendation")
    public String addRecommendation(@Valid RecommendationImportDto recommendationImportDto,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("recommendationImportDto", recommendationImportDto);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.recommendationImportDto",
                            bindingResult);

            return "redirect:/add-recommendation";
        }


        Long loggedUserId = userEntityService.getLoggedUserId(currentUserNameEmail);

        userEntityService.addNewRecommendation(recommendationImportDto, loggedUserId);

        return "redirect:/about";
    }











}
