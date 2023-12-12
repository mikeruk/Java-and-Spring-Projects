package com.example.spring20232.web;


import com.example.spring20232.binding.viewDTO.WorkExpHateoasViewDto;
import com.example.spring20232.model.exceptions.SkillEntityNotFoundException;
import com.example.spring20232.model.exceptions.WorkExpEntityNotFoundException;
import com.example.spring20232.service.WorkExpService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.hateoas.Link;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@CrossOrigin("*")
@RestController
@RequestMapping()
public class WorkExpRestController {


    private final WorkExpService workExpService;

    public WorkExpRestController(WorkExpService workExpService) {
        this.workExpService = workExpService;
    }




    @GetMapping("/work-exp-rest")
    public ResponseEntity<CollectionModel<EntityModel<WorkExpHateoasViewDto>>> getAllWorkExpHateoasViewDto() {

        List<EntityModel<WorkExpHateoasViewDto>> entityModels = workExpService.getAllWorkExpHateaosViewDtos().
                stream().map(
                        s -> EntityModel.of(s, getWorkExpHateoasViewDtoLinks(s))).toList();

        CollectionModel<EntityModel<WorkExpHateoasViewDto>> collectionModel =
                CollectionModel.of(entityModels);


        return ResponseEntity.ok(collectionModel);
    }


    @GetMapping("/work-exp-rest/{id}")
    public ResponseEntity<WorkExpHateoasViewDto> getWorkExpById(@PathVariable("id") Long id) {

        WorkExpHateoasViewDto workExpHateaosViewDtosByID = workExpService.getSingleWorkExpHateaosViewDtosByID(id);
        System.out.println();
        return ResponseEntity.ok(workExpHateaosViewDtosByID);
    }

    @ExceptionHandler(WorkExpEntityNotFoundException.class)
    public ModelAndView onWorkExpNotFound(WorkExpEntityNotFoundException workExpEntityNotFoundException){
        ModelAndView modelAndView = new ModelAndView("error/404");
        modelAndView.addObject("entityId", workExpEntityNotFoundException.getEntityId());
        return modelAndView;
    }


    @PutMapping("/work-exp-rest/{id}")
    public ResponseEntity<WorkExpHateoasViewDto> updateWorkExp(
            @PathVariable("id") Long updateWorkExpID,
            WorkExpHateoasViewDto workExpHateoasViewDto) {
        throw new UnsupportedOperationException("This operation is not supported");
    }


    private Link[] getWorkExpHateoasViewDtoLinks(WorkExpHateoasViewDto workExpHateoasViewDto) {
        List<Link> workExpHateoasViewDtoLinks = new ArrayList<>();

        Link selfLink = linkTo(
                methodOn(WorkExpRestController.class).getWorkExpById(workExpHateoasViewDto.getId())).
                withSelfRel();

        workExpHateoasViewDtoLinks.add(selfLink);


        Link workExpLink = linkTo(methodOn(WorkExpRestController.class).getWorkExpById(workExpHateoasViewDto.getId())).withRel("WorkExp-GET-LINK");

        workExpHateoasViewDtoLinks.add(workExpLink);


        return workExpHateoasViewDtoLinks.toArray(new Link[0]);
    }


}
