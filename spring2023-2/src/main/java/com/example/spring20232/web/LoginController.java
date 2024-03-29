package com.example.spring20232.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {


  @GetMapping("/users/login")
  public String login() {
    System.out.println();
    return "auth-login1";
  }



  @PostMapping("/users/login-error")
  public String onFailedLogin(
          @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
          RedirectAttributes redirectAttributes) {

    redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
    redirectAttributes.addFlashAttribute("bad_credentials", true);

    return "redirect:/users/login";
  }





}
