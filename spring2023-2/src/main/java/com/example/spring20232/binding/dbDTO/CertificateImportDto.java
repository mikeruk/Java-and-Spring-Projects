package com.example.spring20232.binding.dbDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CertificateImportDto {



    private String professionalField;
    private String name;
    private String organisation;
    private LocalDate issueDate;


    public CertificateImportDto() {
    }




    @Size(min = 2, max = 50)
    public String getProfessionalField() {
        return professionalField;
    }

    public void setProfessionalField(String professionalField) {
        this.professionalField = professionalField;
    }

    @Size(min = 2, max = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(min = 2, max = 50)
    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    @PastOrPresent
    @NotNull
    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
}
