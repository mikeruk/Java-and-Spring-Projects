package com.example.spring20232.web;


import com.example.spring20232.binding.dbDTO.SkillsImportDto;
import com.example.spring20232.binding.viewDTO.SkillsViewDto;
import com.example.spring20232.service.SkillsService;
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

@Controller()
public class SkillsController {
    private final UserEntityService userEntityService;
    private String currentUserNameEmail;

    private final SkillsService skillsService;

    public SkillsController(UserEntityService userEntityService, SkillsService skillsService) {
        this.userEntityService = userEntityService;
        this.skillsService = skillsService;
    }


    @ModelAttribute
    public SkillsViewDto skillsViewDto() {
        return new SkillsViewDto();
    }


    @GetMapping("/skills")
    public String home(Model model) {

        currentUserNameEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!currentUserNameEmail.equals("anonymousUser")) {

            Long loggedUserId = userEntityService.getLoggedUserId(currentUserNameEmail);
            model.addAttribute("id", loggedUserId);
        }


        List<SkillsViewDto> allSkillsViewDtoList = skillsService.findAllSkills();
        model.addAttribute("skillsViewDtoList", allSkillsViewDtoList);

        return "my-skills-page";
    }


    @ModelAttribute
    public SkillsImportDto skillsImportDto() {
        return new SkillsImportDto();
    }

    @GetMapping("/skills/add")
    public String addSkill(Model model) {

        currentUserNameEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!currentUserNameEmail.equals("anonymousUser")) {

            Long loggedUserId = userEntityService.getLoggedUserId(currentUserNameEmail);
            model.addAttribute("id", loggedUserId);
        }
        return "add-skill";
    }

    @PostMapping("/skills/add")
    public String saveSkill(@Valid SkillsImportDto skillsImportDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("skillsImportDto", skillsImportDto);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.skillsImportDto",
                            bindingResult);

            return "redirect:/skills/add";
        }

        skillsService.addNewSkill(skillsImportDto);

        return "redirect:/skills";
    }


}
