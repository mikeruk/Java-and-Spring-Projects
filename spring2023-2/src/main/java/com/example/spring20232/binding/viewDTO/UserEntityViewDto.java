package com.example.spring20232.binding.viewDTO;



import com.example.spring20232.model.entity.Recommendation;
import com.example.spring20232.model.entity.UserRoleEntity;

import java.util.ArrayList;
import java.util.List;


public class UserEntityViewDto {


    private Long id;
    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private List<Recommendation> recommendations;
    private List<RecommendationViewDto> recommendationsView;


    private List<UserRoleEntity> roles = new ArrayList<>();

    private String singleRole;

    public UserEntityViewDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getSingleRole() {
        return this.roles.get(0).getRole().name();
    }

    public void setSingleRole() {
        this.singleRole = this.roles.get(0).getRole().name();
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    public void setSingleRole(String singleRole) {
        this.singleRole = singleRole;
    }

    public List<RecommendationViewDto> getRecommendationsView() {
        return recommendationsView;
    }

    public void setRecommendationsView(List<RecommendationViewDto> recommendationsView) {
        this.recommendationsView = recommendationsView;
    }





}
