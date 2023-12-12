package com.example.spring20232.model.exceptions;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/404");

        return modelAndView;
    }
}
