package com.example.spring20232.binding.dbDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RecommendationImportDto {

    private Long id;


    private String name;


    private String position;

    private String telephoneNumber;


    private String email;


    public RecommendationImportDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Size(min = 2, max = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Size(min = 2, max = 30)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
    @Email()
    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




}
