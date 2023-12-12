package com.example.spring20232.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "work experience")
public class WorkExp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;
    private String employerName;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean stillPresent;
    private String jobDescription;
    private String responsibilities;

    public WorkExp() {
    }

    @Column(name = "Job Title", nullable = false)
    @Size(min = 2, max = 50)
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Column(name = "Name of Employer", nullable = false)
    @Size(min = 2, max = 50)
    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    @Column(name = "Start Date", nullable = false)
    @PastOrPresent
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Column(name = "End Date")
    @PastOrPresent
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Column(name = "Still working there", nullable = true)
    public boolean isStillPresent() {
        return stillPresent;
    }

    public void setStillPresent(boolean stillPresent) {
        this.stillPresent = stillPresent;
    }

    @Column(name = "Job Description", nullable = false)
    @Size(min = 2, max = 1800)
    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    @Column(name = "Main Responsibilities", nullable = false)
    @Size(min = 2, max = 700)
    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
