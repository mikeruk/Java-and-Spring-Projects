package com.example.spring20232.binding.dbDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EndorsementImportDto {

    private Long id;


    private String name;


    private String position;

    private String companyName;


    private String email;

    public EndorsementImportDto() {
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

    @Size(min = 2, max = 30)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
