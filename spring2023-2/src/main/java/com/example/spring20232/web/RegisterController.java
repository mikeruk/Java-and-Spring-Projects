package com.example.spring20232.web;


import com.example.spring20232.binding.dbDTO.UserRegisterBindingDto;
import com.example.spring20232.service.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller()
public class RegisterController {


    private final UserEntityService userEntityService;

    public RegisterController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }




    @ModelAttribute
    public UserRegisterBindingDto userRegisterBindingDto() {
        return new UserRegisterBindingDto();
    }

    @GetMapping("/users/register")
    public String register(Model model) {




        if (model.getAttribute("exists") != null) {

            return "auth-register1";
        } else {
            model.addAttribute("exists", false);
        }

        return "auth-register1";
    }


    @PostMapping("/users/register")
    public String registerConfirm(@Valid UserRegisterBindingDto userRegisterBindingDto,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){

        redirectAttributes.addAttribute("exists", false);
        if (bindingResult.hasErrors() || !userRegisterBindingDto.getPassword().equals(userRegisterBindingDto.getConfirmPassword())) {

            if(!userRegisterBindingDto.getPassword().equals(userRegisterBindingDto.getConfirmPassword())){
                redirectAttributes.addFlashAttribute("passwordsDoNotMatch", true);
                redirectAttributes
                        .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingDto",
                                bindingResult);
            }

            redirectAttributes
                    .addFlashAttribute("userRegisterBindingDto", userRegisterBindingDto);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingDto",
                            bindingResult);
            return "redirect:/users/register";
        }

        redirectAttributes.addAttribute("exists", false);
        boolean userWasRegistered = userEntityService.registerUser(userRegisterBindingDto);

        if (userWasRegistered) {
            redirectAttributes
                    .addFlashAttribute("exists", true)
                    .addFlashAttribute("userRegisterBindingDto", userRegisterBindingDto);

            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingDto",
                            bindingResult);
            return "redirect:/users/register";
        }

        return "redirect:/users/login";
    }
}
