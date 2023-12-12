package com.example.spring20232.binding.dbDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class EducationImportDto {

    private String degree;
    private String organisation;

    private String discipline;
    private LocalDate startDate;
    private LocalDate endDate;

    private boolean dateOrder;


    public EducationImportDto() {

    }

    @Size(min = 2, max = 20)
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Size(min = 2, max = 50)
    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    @Size(min = 2, max = 50)
    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
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
    @NotNull
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

}
