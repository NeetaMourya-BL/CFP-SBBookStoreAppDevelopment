package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.UserRegistrationDTO;
import com.bridgelabz.bookstoreapp.exception.UserRegistrationDataException;
import com.bridgelabz.bookstoreapp.model.UserRegistrationData;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import com.bridgelabz.bookstoreapp.util.EmailListener;
import com.bridgelabz.bookstoreapp.util.EmailSenderService;
import com.bridgelabz.bookstoreapp.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class UserRegistrationService implements IUserRegistrationService {
    //inside this layer we communication between a controller and repository layer
    //Dependency injection
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    private EmailListener emaillistner;

    @Autowired
    private EmailSenderService sender;
    @Override
    public UserRegistrationData createBookStoreData(UserRegistrationDTO userRegistrationDTO) {
        UserRegistrationData userRegistrationData = new UserRegistrationData(userRegistrationDTO);
//        emaillistner.sendMail();
        sender.sendEmail("neeta.mourya@bridgelabz.com", "Test Email", "Registered SuccessFully");
        return userRegistrationRepository.save(userRegistrationData);
    }

    @Override
    public List<UserRegistrationData> getBookStoreData() {
        return userRegistrationRepository.findAll();
    }

    @Override
    public UserRegistrationData getBookStoreDataById(String token) {
        System.out.println(token);
        System.out.println(tokenUtil.decodeToken(token));
        return userRegistrationRepository.findById(tokenUtil.decodeToken(token))
                .orElseThrow(() -> new UserRegistrationDataException("User With userId: " + tokenUtil.decodeToken(token) + " does not exists"));
    }

    @Override
    public UserRegistrationData getBookStoreDataById(long id) {
        return userRegistrationRepository.findById(tokenUtil.decodeToken(String.valueOf(id)))
                .orElseThrow(() -> new UserRegistrationDataException("User With userId: " + tokenUtil.decodeToken(String.valueOf(id)) + " does not exists"));
    }

    @Override
    public UserRegistrationData changeUserPassword (String token, UserRegistrationDTO userRegistrationDTO) {
        UserRegistrationData userRegistrationData = this.getBookStoreDataById(token);
        userRegistrationData.setPassword(userRegistrationDTO.password);
        return userRegistrationRepository.save(userRegistrationData);
    }

    @Override
    public List<UserRegistrationData> getbyEmailID(String email) {
        return userRegistrationRepository.getbyEmailID(email);
    }

    @Override
    public UserRegistrationData updateUserData(String token, UserRegistrationDTO userRegistrationDTO) {
        UserRegistrationData userRegistrationData = this.getBookStoreDataById(token);
        userRegistrationData.updateUserData(userRegistrationDTO);
        return userRegistrationRepository.save(userRegistrationData);
}

}