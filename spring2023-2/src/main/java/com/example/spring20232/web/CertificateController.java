package com.example.spring20232.web;


import com.example.spring20232.binding.dbDTO.CertificateImportDto;
import com.example.spring20232.binding.viewDTO.CertificationViewDto;
import com.example.spring20232.service.CertificateService;
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

@Controller
public class CertificateController {

    private final UserEntityService userEntityService;
    private String currentUserNameEmail;

    private final CertificateService certificateService;

    public CertificateController(UserEntityService userEntityService, CertificateService certificateService) {
        this.userEntityService = userEntityService;
        this.certificateService = certificateService;
    }


    @ModelAttribute
    public CertificationViewDto certificationViewDto() {
        return new CertificationViewDto();
    }

    @GetMapping("/certificates")
    public String certHome(Model model) {


        currentUserNameEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!currentUserNameEmail.equals("anonymousUser")) {

            Long loggedUserId = userEntityService.getLoggedUserId(currentUserNameEmail);
            model.addAttribute("id", loggedUserId);
        }


        List<CertificationViewDto> allCertificationViewList = certificateService.findAllCertificationView();
        model.addAttribute("allCertificationViewList", allCertificationViewList);
        return "my-certificates-page";
    }


    @ModelAttribute
    public CertificateImportDto certificateImportDto() {
        return new CertificateImportDto();
    }


    @GetMapping("/certificates/add")
    public String addCertificate(Model model) {

        currentUserNameEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!currentUserNameEmail.equals("anonymousUser")) {

            Long loggedUserId = userEntityService.getLoggedUserId(currentUserNameEmail);
            model.addAttribute("id", loggedUserId);
        }

        return "add-certificate";
    }

    @PostMapping("/certificates/add")
    public String addNewCertificate(@Valid CertificateImportDto certificateImportDto,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            System.out.println();
            redirectAttributes
                    .addFlashAttribute("certificateImportDto", certificateImportDto);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.certificateImportDto",
                            bindingResult);

            System.out.println();
            return "redirect:/certificates/add";
        }

        certificateService.saveNewCert(certificateImportDto);


        return "redirect:/certificates";

    }


}
