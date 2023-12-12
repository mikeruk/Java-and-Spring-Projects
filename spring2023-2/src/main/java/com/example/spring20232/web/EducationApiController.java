package com.example.spring20232.web;


import com.example.spring20232.service.UserEntityService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping()
public class EducationApiController {

    private final UserEntityService userEntityService;


    private String currentUserNameEmail;

    public EducationApiController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }


    @GetMapping("/education-api")
    public String educationApi(Model model){

        currentUserNameEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!currentUserNameEmail.equals("anonymousUser")) {

            Long loggedUserId = userEntityService.getLoggedUserId(currentUserNameEmail);
            System.out.println(loggedUserId);
            model.addAttribute("id", loggedUserId);
        }

        return "my-education-rest";
    }
}
