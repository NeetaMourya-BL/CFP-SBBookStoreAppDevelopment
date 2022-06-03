package com.bridgelabz.bookstoreapp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserRegistrationDTO {

    //apply regex pattern for enter any data
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "user firstname invalid it should be start with capital letter")
    @NotEmpty(message = "Person firstName can't be null")
    public String firstName;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "user lastname invalid it should be start with capital letter")
    @NotEmpty(message = "user lastName can't be null")
    public String lastName;
    @NotEmpty(message = "email can not be empty")
    public String email;
    @NotEmpty(message = "address can not be empty")
    public String address;
    @Pattern(regexp = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$", message = "user password invalid at least 8 characters and at most 20 character " +
            "a lower case alphabet must occur at least once " +
            "an upper case alphabet that must occur at least once " +
            "a digit must occur at least once " +
            "a special character that must occur at least once.")
    @NotEmpty(message = "password can not be empty")
    public String password;
}
