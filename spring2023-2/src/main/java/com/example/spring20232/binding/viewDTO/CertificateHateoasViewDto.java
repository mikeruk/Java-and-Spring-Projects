package com.example.spring20232.binding.viewDTO;


import java.time.LocalDate;

public class CertificateHateoasViewDto {


    private Long id;
    private String professionalField;
    private String name;
    private String organisation;
    private LocalDate issueDate;




    public CertificateHateoasViewDto() {
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfessionalField() {
        return professionalField;
    }

    public void setProfessionalField(String professionalField) {
        this.professionalField = professionalField;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }






}
