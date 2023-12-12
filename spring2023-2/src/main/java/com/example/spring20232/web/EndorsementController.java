package com.example.spring20232.web;


import com.example.spring20232.binding.dbDTO.EndorsementImportDto;
import com.example.spring20232.service.EndorsementService;
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

@Controller()
public class EndorsementController {


    private String currentUserNameEmail;
    private final UserEntityService userEntityService;

    private final EndorsementService endorsementService;

    public EndorsementController(UserEntityService userEntityService, EndorsementService endorsementService) {
        this.userEntityService = userEntityService;
        this.endorsementService = endorsementService;
    }


    @GetMapping("/add-endorsement")
    public String endorsement(Model model){

        currentUserNameEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!currentUserNameEmail.equals("anonymousUser")) {

            Long loggedUserId = userEntityService.getLoggedUserId(currentUserNameEmail);
            model.addAttribute("id", loggedUserId);
        }
        return "add-endorsement";
    }

    @ModelAttribute
    public EndorsementImportDto endorsementImportDto(){
        return new EndorsementImportDto();
    }



    @PostMapping("/add-endorsement")
    public String addEndorsement(@Valid EndorsementImportDto endorsementImportDto,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            System.out.println();
            redirectAttributes
                    .addFlashAttribute("endorsementImportDto", endorsementImportDto);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.endorsementImportDto",
                            bindingResult);

            return "redirect:/add-endorsement";
        }


        Long loggedUserId = userEntityService.getLoggedUserId(currentUserNameEmail);

        endorsementService.addNewEndorsement(endorsementImportDto);

        return "redirect:/about";
    }




}
