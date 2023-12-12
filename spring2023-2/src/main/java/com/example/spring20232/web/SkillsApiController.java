package com.example.spring20232.web;


import com.example.spring20232.service.UserEntityService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping()
public class SkillsApiController {

    private String currentUserNameEmail;
    private final UserEntityService userEntityService;

    public SkillsApiController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }


    @GetMapping("/skills-api")
    public String skillsApi(Model model){

        currentUserNameEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!currentUserNameEmail.equals("anonymousUser")) {

            Long loggedUserId = userEntityService.getLoggedUserId(currentUserNameEmail);
            model.addAttribute("id", loggedUserId);
        }


        return "my-skills-rest";
    }


}
