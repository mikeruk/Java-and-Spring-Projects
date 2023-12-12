package com.example.spring20232.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "Skills")
public class Skills {


    private Long id;
    private String skillDescription;

    public Skills() {
    }

    @Column(name = "Skill Description", nullable = false)
    @Size(min = 2, max = 30)
    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
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
