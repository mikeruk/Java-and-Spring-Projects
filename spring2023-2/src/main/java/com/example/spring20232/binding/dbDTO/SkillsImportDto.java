package com.example.spring20232.binding.dbDTO;

import jakarta.validation.constraints.Size;

public class SkillsImportDto {

    private String skillDescription;


    public SkillsImportDto() {
    }

    @Size(min = 2, max = 30)
    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }
}
