package com.example.spring20232.web;

import com.example.spring20232.binding.dbDTO.WorkExpEditImportDto;
import com.example.spring20232.binding.viewDTO.WorkExpViewEdit_ID_dto;
import com.example.spring20232.service.WorkExpService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EditWorkExpController {

    private final WorkExpService workExpService;

    public EditWorkExpController(WorkExpService workExpService) {
        this.workExpService = workExpService;
    }


    @ModelAttribute
    public WorkExpEditImportDto workExpEditImportDto() {
        return new WorkExpEditImportDto();
    }

    @ModelAttribute
    public WorkExpViewEdit_ID_dto workExpViewEdit_id_dto() {
        return new WorkExpViewEdit_ID_dto();
    }


    @GetMapping("/work-experience/edit/{id}")
    public String editWorkExp(@PathVariable("id") Long id,
                              Model model,
                              WorkExpViewEdit_ID_dto workExpViewEdit_ID_dto) {



        System.out.println();
        workExpViewEdit_ID_dto.setId(id);
        model.addAttribute("workExpViewEdit_ID_dto", workExpViewEdit_ID_dto);


        if (model.getAttribute("checkedDate") != null) {
            System.out.println(model.getAttribute("checkedDate"));
            return "edit-work-experience";
        } else {
            model.addAttribute("checkedDate", false);
        }

        return "edit-work-experience";
    }


    @PostMapping("/work-experience/edit/{id}")
    public String addWorkExp(@Valid WorkExpEditImportDto workExpEditImportDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @PathVariable("id") Long id,
                             Model model) {



        if (workExpEditImportDto.getEndDate() == null) {
            if (bindingResult.hasErrors()) {
                if (workExpEditImportDto.checkDateOrder()) {
                    this.workExpEditImportDto().setDateOrder(true);
                    redirectAttributes
                            .addFlashAttribute("checkedDate", true);

                }

                redirectAttributes
                        .addFlashAttribute("workExpEditImportDto", workExpEditImportDto);
                redirectAttributes
                        .addFlashAttribute("org.springframework.validation.BindingResult.workExpEditImportDto",
                                bindingResult);
                return "redirect:/work-experience/edit";
            }


        } else {
            if (bindingResult.hasErrors() || workExpEditImportDto.checkDateOrder()) {
                if (workExpEditImportDto.checkDateOrder()) {
                    this.workExpEditImportDto().setDateOrder(true);
                    redirectAttributes
                            .addFlashAttribute("checkedDate", true);

                }

                redirectAttributes
                        .addFlashAttribute("workExpEditImportDto", workExpEditImportDto);
                redirectAttributes
                        .addFlashAttribute("org.springframework.validation.BindingResult.workExpImportDto",
                                bindingResult);
                return "redirect:/work-experience/edit";
            }
        }


        System.out.println("all tests passed");
        workExpService.editThisWorkExp(id, workExpEditImportDto);


        return "redirect:/work-experience";
    }


    @GetMapping("/work-experience/edit")
    public String getWorkExpToEdit(Model model) {

        if (model.getAttribute("checkedDate") != null) {
            return "edit-work-experience";
        } else {
            model.addAttribute("checkedDate", false);
        }

        return "edit-work-experience";
    }


}
