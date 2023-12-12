package com.example.spring20232.binding.dbDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class WorkExpEditImportDto {


    private Long id;

    private String jobTitle;

    private String employerName;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean stillPresent;

    private String jobDescription;

    private String responsibilities;

    private boolean dateOrder;


    public WorkExpEditImportDto() {
    }

    @Size(min = 2, max = 50)
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Size(min = 2, max = 50)
    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    @PastOrPresent
    @NotNull
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @PastOrPresent

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

    @Size(min = 2, max = 1800)
    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    @Size(min = 2, max = 700)
    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }


    public boolean isDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(boolean dateOrder) {
        this.dateOrder = dateOrder;
    }


    public boolean checkDateOrder() {

        if (this.startDate != null) {
            if (this.startDate.isAfter(this.endDate)) {
                return true;
            }
        }

        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
