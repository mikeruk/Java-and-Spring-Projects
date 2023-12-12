package com.example.spring20232.binding.dbDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserRegisterBindingDto {



    @Size(min = 3, max = 15)
    private String firstName;

    @Size(min = 5, max = 15)
    private String lastName;

    @Email()
    private String email;

    @Size(min = 4)
    private String password;

    @Size(min = 4)
    private String confirmPassword;

    boolean alreadyRegistered;



    public UserRegisterBindingDto() {

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


    public boolean isAlreadyRegistered() {
        return alreadyRegistered;
    }

    public void setAlreadyRegistered(boolean alreadyRegistered) {
        this.alreadyRegistered = alreadyRegistered;
    }
}
