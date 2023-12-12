package com.example.spring20232.web;


import com.example.spring20232.service.UserEntityService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping()
public class WorkExpApiController {

    private final UserEntityService userEntityService;
    private String currentUserNameEmail;

    public WorkExpApiController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }


    @GetMapping("/work-exp-api")
    public String workExpApi(Model model){

        currentUserNameEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!currentUserNameEmail.equals("anonymousUser")) {

            Long loggedUserId = userEntityService.getLoggedUserId(currentUserNameEmail);
            model.addAttribute("id", loggedUserId);
        }


        return "my-work-exp-rest";
    }


}
