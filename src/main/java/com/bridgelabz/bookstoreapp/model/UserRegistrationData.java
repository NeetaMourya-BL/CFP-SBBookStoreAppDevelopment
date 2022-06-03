package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.UserRegistrationDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity //use for create database and table using @Data we don't need to define any getter setter contructor and to string method
@Table(name = "userregistrationdb")
public @Data class UserRegistrationData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    private long userId;

    @Column(name = "firstName")
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String password;
    private boolean loggedIn;


    public UserRegistrationData(UserRegistrationDTO userRegistrationDTO) {
        this.updateUserData(userRegistrationDTO);
    }
    public UserRegistrationData() {

    }

    public UserRegistrationData(String firstName, String lastName, String email, String address, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.password = password;
        this.loggedIn = false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRegistrationData)) return false;
        UserRegistrationData user = (UserRegistrationData) o;
        return Objects.equals(userId, user.userId)&&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, password,
                loggedIn);
    }

    public void updateUserData(UserRegistrationDTO userRegistrationDTO) {
        this.firstName = userRegistrationDTO.firstName;
        this.lastName = userRegistrationDTO.lastName;
        this.email = userRegistrationDTO.email;
        this.address = userRegistrationDTO.address;
        this.password = userRegistrationDTO.password;
    }
}