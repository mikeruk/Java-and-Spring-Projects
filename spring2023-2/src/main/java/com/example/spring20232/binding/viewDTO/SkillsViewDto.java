package com.example.spring20232.binding.viewDTO;

public class SkillsViewDto {


    private Long id;
    private String skillDescription;

    public SkillsViewDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }
}
