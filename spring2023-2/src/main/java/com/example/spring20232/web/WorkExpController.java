package com.example.spring20232.web;


import com.example.spring20232.binding.dbDTO.WorkExpImportDto;
import com.example.spring20232.binding.viewDTO.WorkExpViewDto;
import com.example.spring20232.service.UserEntityService;
import com.example.spring20232.service.WorkExpService;
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
public class WorkExpController {

    private final UserEntityService userEntityService;
    private String currentUserNameEmail;

    private final WorkExpService workExpService;

    public WorkExpController(UserEntityService userEntityService, WorkExpService workExpService) {
        this.userEntityService = userEntityService;
        this.workExpService = workExpService;
    }



    @GetMapping("/work-experience")
    public String myWorkExp(Model model) {

        currentUserNameEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!currentUserNameEmail.equals("anonymousUser")) {

            Long loggedUserId = userEntityService.getLoggedUserId(currentUserNameEmail);
            model.addAttribute("id", loggedUserId);
        }

        List<WorkExpViewDto> workExpViewDtoList = workExpService.findAllWorkExpView();
        model.addAttribute("workExpViewDtoList", workExpViewDtoList);

        return "my-work-experience-page";
    }


    @ModelAttribute
    public WorkExpImportDto workExpImportDto() {
        return new WorkExpImportDto();
    }


    @GetMapping("/work-experience/add")
    public String getWorkExp(Model model) {


        currentUserNameEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!currentUserNameEmail.equals("anonymousUser")) {

            Long loggedUserId = userEntityService.getLoggedUserId(currentUserNameEmail);
            model.addAttribute("id", loggedUserId);
        }


        if (model.getAttribute("checkedDate") != null) {
            return "add-work-experience";
        } else {
            model.addAttribute("checkedDate", false);
        }

        return "add-work-experience";
    }


    @PostMapping("/work-experience/add")
    public String addWorkExp(@Valid WorkExpImportDto workExpImportDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {



        if (workExpImportDto.getEndDate() == null) {
            if (bindingResult.hasErrors()) {

                redirectAttributes
                        .addFlashAttribute("workExpImportDto", workExpImportDto);
                redirectAttributes
                        .addFlashAttribute("org.springframework.validation.BindingResult.workExpImportDto",
                                bindingResult);
                return "redirect:/work-experience/add";
            }


        } else {
            if (bindingResult.hasErrors() || workExpImportDto.checkDateOrder()) {
                if (workExpImportDto.checkDateOrder()) {
                    this.workExpImportDto().setDateOrder(true);
                    redirectAttributes
                            .addFlashAttribute("checkedDate", true);

                }

                redirectAttributes
                        .addFlashAttribute("workExpImportDto", workExpImportDto);
                redirectAttributes
                        .addFlashAttribute("org.springframework.validation.BindingResult.workExpImportDto",
                                bindingResult);
                return "redirect:/work-experience/add";
            }
        }


        workExpService.addNewWorkExp(workExpImportDto);


        return "redirect:/work-experience";
    }



}
