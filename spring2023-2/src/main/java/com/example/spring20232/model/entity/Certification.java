package com.example.spring20232.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "Certification")
public class Certification {

    private Long id;

    private String professionalField;
    private String name;
    private String organisation;
    private LocalDate issueDate;

    public Certification() {
    }

    @Column(name = "Professional Field", nullable = false)
    @Size(min = 2, max = 50)
    public String getProfessionalField() {
        return professionalField;
    }

    public void setProfessionalField(String professionalField) {
        this.professionalField = professionalField;
    }

    @Column(name = "Name", nullable = false)
    @Size(min = 2, max = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Organisation", nullable = false)
    @Size(min = 2, max = 50)
    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    @Column(name = "Issued", nullable = false)
    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
