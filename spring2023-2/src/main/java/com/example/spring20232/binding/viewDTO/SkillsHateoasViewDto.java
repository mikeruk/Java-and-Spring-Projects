package com.example.spring20232.binding.viewDTO;



public class SkillsHateoasViewDto {

    private Long id;
    private String skillDescription;

    public SkillsHateoasViewDto() {
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
