package com.example.spring20232.web;


import com.example.spring20232.binding.viewDTO.EducationHateoasViewDto;
import com.example.spring20232.binding.viewDTO.WorkExpHateoasViewDto;
import com.example.spring20232.model.exceptions.EducationEntityNotFoundException;
import com.example.spring20232.model.exceptions.SkillEntityNotFoundException;
import com.example.spring20232.service.EducationService;
import com.example.spring20232.service.WorkExpService;
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
public class EducationRestController {


    private final EducationService educationService;


    public EducationRestController(EducationService educationService) {
        this.educationService = educationService;
    }





    @GetMapping("/education-rest")
    public ResponseEntity<CollectionModel<EntityModel<EducationHateoasViewDto>>> getAllEducationHateoasViewDto() {


        List<EntityModel<EducationHateoasViewDto>> entityModels = educationService.getAllEducationHateaosViewDtos().
                stream().map(
                        s -> EntityModel.of(s, getEducationHateoasViewDtoLinks(s))).toList();

        CollectionModel<EntityModel<EducationHateoasViewDto>> collectionModel =
                CollectionModel.of(entityModels);



        return ResponseEntity.ok(collectionModel);
    }


    @GetMapping("/education-rest/{id}")
    public ResponseEntity<EducationHateoasViewDto> getEducationById(@PathVariable("id") Long id) {

        EducationHateoasViewDto educationHateaosViewDtosByID = educationService.getSingleEducationHateaosViewDtosByID(id);
        System.out.println();
        return ResponseEntity.ok(educationHateaosViewDtosByID);
    }

    @ExceptionHandler(EducationEntityNotFoundException.class)
    public ModelAndView onSkillNotFound(EducationEntityNotFoundException educationEntityNotFoundException){
        ModelAndView modelAndView = new ModelAndView("error/404");
        modelAndView.addObject("entityId", educationEntityNotFoundException.getEntityId());
        return modelAndView;
    }


    @PutMapping("/education-rest/{id}")
    public ResponseEntity<EducationHateoasViewDto> updateEducation(
            @PathVariable("id") Long updateEducationID,
            EducationHateoasViewDto educationHateoasViewDto) {
        throw new UnsupportedOperationException("This operation is not supported");
    }


    private Link[] getEducationHateoasViewDtoLinks(EducationHateoasViewDto educationHateoasViewDto) {
        List<Link> educationHateoasViewDtoLinks = new ArrayList<>();

        Link selfLink = linkTo(
                methodOn(EducationRestController.class).getEducationById(educationHateoasViewDto.getId())).
                withSelfRel();

       educationHateoasViewDtoLinks.add(selfLink);


        Link educationExpLink = linkTo(methodOn(EducationRestController.class).getEducationById(educationHateoasViewDto.getId())).withRel("education-GET-LINK");

        educationHateoasViewDtoLinks.add(educationExpLink);


        return educationHateoasViewDtoLinks.toArray(new Link[0]);
    }










}
