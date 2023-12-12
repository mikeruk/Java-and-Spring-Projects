package com.example.spring20232.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "Education")
public class Education {



    private Long id;

    private String degree;
    private String organisation;

    private String discipline;
    private LocalDate startDate;
    private LocalDate endDate;

    public Education() {
    }

    @Column(name = "degree", nullable = false)
    @Size(min = 2, max = 20)
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Column(name = "organisation name", nullable = false)
    @Size(min = 2, max = 50)
    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    @Column(name = "Study Discipline", nullable = false)
    @Size(min = 2, max = 50)
    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    @Column(name = "start date", nullable = false)
    @PastOrPresent
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Column(name = "End Date", nullable = false)
    @PastOrPresent
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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
