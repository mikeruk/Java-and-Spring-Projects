package com.example.spring20232.binding.viewDTO;

import java.time.LocalDate;

public class WorkExpViewDto {

    private Long id;

    private String jobTitle;

    private String employerName;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean stillPresent;

    private String jobDescription;

    private String responsibilities;


    public WorkExpViewDto() {
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isStillPresent() {
        return stillPresent;
    }

    public void setStillPresent(boolean stillPresent) {
        this.stillPresent = stillPresent;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

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
