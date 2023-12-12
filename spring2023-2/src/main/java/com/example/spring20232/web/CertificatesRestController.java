package com.example.spring20232.web;


import com.example.spring20232.binding.viewDTO.CertificateHateoasViewDto;
import com.example.spring20232.model.exceptions.CertificationEntityNotFoundException;
import com.example.spring20232.model.exceptions.SkillEntityNotFoundException;
import com.example.spring20232.service.CertificateService;
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
public class CertificatesRestController {


    private final CertificateService certificateService;

    public CertificatesRestController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }



    @GetMapping("/certificates-rest")
    public ResponseEntity<CollectionModel<EntityModel<CertificateHateoasViewDto>>> getAllCertificateHateoasViewDto() {

        List<EntityModel<CertificateHateoasViewDto>> entityModels = certificateService.getAllCertificateHateaosViewDtos().
                stream().map(
                        s -> EntityModel.of(s, getCertificateHateoasViewDtoLinks(s))).toList();

        CollectionModel<EntityModel<CertificateHateoasViewDto>> collectionModel =
                CollectionModel.of(entityModels);



        return ResponseEntity.ok(collectionModel);
    }


    @GetMapping("/certificates-rest/{id}")
    public ResponseEntity<CertificateHateoasViewDto> getCertificateById(@PathVariable("id") Long id) {

        CertificateHateoasViewDto certificateHateaosViewDtosByID = certificateService.getSingleCertificateHateaosViewDtoByID(id);
        return ResponseEntity.ok(certificateHateaosViewDtosByID);
    }

    @ExceptionHandler(CertificationEntityNotFoundException.class)
    public ModelAndView onCertificateNotFound(CertificationEntityNotFoundException certificationEntityNotFoundException){
        ModelAndView modelAndView = new ModelAndView("error/404");
        modelAndView.addObject("entityId", certificationEntityNotFoundException.getEntityId());
        return modelAndView;
    }


    @PutMapping("/certificates-rest/{id}")
    public ResponseEntity<CertificateHateoasViewDto> updateCertificate(
            @PathVariable("id") Long updateCertificateID,
            CertificateHateoasViewDto certificateHateoasViewDto) {
        throw new UnsupportedOperationException("This operation is not supported");
    }


    private Link[] getCertificateHateoasViewDtoLinks(CertificateHateoasViewDto certificateHateoasViewDto) {
        List<Link> certificateHateoasViewDtoLinks = new ArrayList<>();

        Link selfLink = linkTo(
                methodOn(CertificatesRestController.class).getCertificateById(certificateHateoasViewDto.getId())).
                withSelfRel();

        certificateHateoasViewDtoLinks.add(selfLink);


        Link certificateLink = linkTo(methodOn(CertificatesRestController.class).getCertificateById(certificateHateoasViewDto.getId())).withRel("certificate-GET-LINK");

        certificateHateoasViewDtoLinks.add(certificateLink);


        return certificateHateoasViewDtoLinks.toArray(new Link[0]);
    }


}
