package com.example.spring20232.web;


import com.example.spring20232.binding.dbDTO.EducationImportDto;
import com.example.spring20232.binding.viewDTO.EducationViewDto;
import com.example.spring20232.service.EducationService;
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

import java.util.List;

@Controller
public class EducationController {
    private final UserEntityService userEntityService;
    private String currentUserNameEmail;

    private final EducationService educationService;

    public EducationController(UserEntityService userEntityService, EducationService educationService) {
        this.userEntityService = userEntityService;
        this.educationService = educationService;
    }



    @ModelAttribute
    public EducationViewDto educationViewDto(){
        return new EducationViewDto();
    }

    @GetMapping("/education")
    public String myEducation(Model model) {


        currentUserNameEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!currentUserNameEmail.equals("anonymousUser")) {

            Long loggedUserId = userEntityService.getLoggedUserId(currentUserNameEmail);
            model.addAttribute("id", loggedUserId);
        }

        List<EducationViewDto> allEducationViewList = educationService.findAllEducationView();
        model.addAttribute("allEducationViewList", allEducationViewList);
        return "my-education-page";
    }


    @ModelAttribute
    public EducationImportDto educationImportDto() {
        return new EducationImportDto();
    }


    @GetMapping("/education/add")
    public String addEducation(Model model) {

        currentUserNameEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!currentUserNameEmail.equals("anonymousUser")) {

            Long loggedUserId = userEntityService.getLoggedUserId(currentUserNameEmail);
            model.addAttribute("id", loggedUserId);
        }


        if (model.getAttribute("checkedDate") != null) {
            return "add-education";
        } else {
            model.addAttribute("checkedDate", false);
        }

        return "add-education";
    }

    @PostMapping("/education/add")
    public String addNewEducation(@Valid EducationImportDto educationImportDto,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || educationImportDto.checkDateOrder()) {
            if (educationImportDto.checkDateOrder()) {
                this.educationImportDto().setDateOrder(true);
                redirectAttributes
                        .addFlashAttribute("checkedDate", true);

            }

            redirectAttributes
                    .addFlashAttribute("educationImportDto", educationImportDto);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.educationImportDto",
                            bindingResult);
            return "redirect:/education/add";
        }

        educationService.addNewEducation(educationImportDto);


        return "redirect:/education";
    }

}
