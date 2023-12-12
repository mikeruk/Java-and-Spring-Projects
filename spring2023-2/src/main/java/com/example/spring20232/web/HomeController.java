package com.example.spring20232.web;


import com.example.spring20232.binding.viewDTO.IncomingRequestPathsCountViewDto;
import com.example.spring20232.service.IncomingRequestService;
import com.example.spring20232.service.UserEntityService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller()
public class HomeController {


    private final UserEntityService userEntityService;



    public HomeController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;

    }




    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        System.out.println();



        final String currentUserNameEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!currentUserNameEmail.equals("anonymousUser")) {

            Long loggedUserId = userEntityService.getLoggedUserId(currentUserNameEmail);
            model.addAttribute("id", loggedUserId);
        }



        return "index1";
    }


}
