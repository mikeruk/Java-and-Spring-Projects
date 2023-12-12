package com.example.spring20232.web;


import com.example.spring20232.binding.viewDTO.SkillsHateoasViewDto;
import com.example.spring20232.binding.viewDTO.WorkExpHateoasViewDto;

import com.example.spring20232.model.exceptions.SkillEntityNotFoundException;
import com.example.spring20232.service.SkillsService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin("*")
@RestController
@RequestMapping()
public class SkillsRestController {


        private final SkillsService skillsService;

    public SkillsRestController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }




    @GetMapping("/skills-rest")
    public ResponseEntity<CollectionModel<EntityModel<SkillsHateoasViewDto>>> getAllSkillsHateoasViewDto() {

        List<EntityModel<SkillsHateoasViewDto>> entityModels = skillsService.getAllSkillsHateaosViewDtos().
                stream().map(
                        s -> EntityModel.of(s, getSkillsHateoasViewDtoLinks(s))).toList();

        CollectionModel<EntityModel<SkillsHateoasViewDto>> collectionModel =
                CollectionModel.of(entityModels);


        return ResponseEntity.ok(collectionModel);
    }


    @GetMapping("/skills-rest/{id}")
    public ResponseEntity<SkillsHateoasViewDto> getSkillsById(@PathVariable("id") Long id) {
        SkillsHateoasViewDto skillsHateaosViewDtosByID = skillsService.getSingleWorkExpHateaosViewDtosByID(id);
        return ResponseEntity.ok(skillsHateaosViewDtosByID);
    }




    @ExceptionHandler(SkillEntityNotFoundException.class)
    public ModelAndView onSkillNotFound(SkillEntityNotFoundException skillEntityNotFoundException){
        ModelAndView modelAndView = new ModelAndView("error/404");
        modelAndView.addObject("entityId", skillEntityNotFoundException.getEntityId());
        return modelAndView;
    }


    @PutMapping("/skills-rest/{id}")
    public ResponseEntity<SkillsHateoasViewDto> updateSkills(
            @PathVariable("id") Long updateSkillsID,
            SkillsHateoasViewDto skillsHateoasViewDto) {
        throw new UnsupportedOperationException("This operation is not supported");
    }


    private Link[] getSkillsHateoasViewDtoLinks(SkillsHateoasViewDto skillsHateoasViewDto) {
        List<Link> skillsHateoasViewDtoLinks = new ArrayList<>();

        Link selfLink = linkTo(
                methodOn(SkillsRestController.class).getSkillsById(skillsHateoasViewDto.getId())).
                withSelfRel();

        skillsHateoasViewDtoLinks.add(selfLink);


        Link skillsLink = linkTo(methodOn(SkillsRestController.class).getSkillsById(skillsHateoasViewDto.getId())).withRel("skills-GET-LINK");

        skillsHateoasViewDtoLinks.add(skillsLink);


        return skillsHateoasViewDtoLinks.toArray(new Link[0]);
    }



}
