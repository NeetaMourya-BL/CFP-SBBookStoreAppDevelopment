package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.UserRegistrationDTO;
import com.bridgelabz.bookstoreapp.model.UserRegistrationData;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IUserRegistrationService {
    //Defind service layer method declaration
    List<UserRegistrationData> getBookStoreData();

    UserRegistrationData getBookStoreDataById(String token);

    UserRegistrationData createBookStoreData(UserRegistrationDTO userRegistrationDTO);

    UserRegistrationData getBookStoreDataById(long id);

    UserRegistrationData changeUserPassword(String token, UserRegistrationDTO userRegistrationDTO);
    List<UserRegistrationData> getbyEmailID(String email);

    UserRegistrationData updateUserData(String token,UserRegistrationDTO userRegistrationDTO);
}
