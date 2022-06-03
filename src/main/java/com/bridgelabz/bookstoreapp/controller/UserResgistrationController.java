package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.UserRegistrationDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.Status;
import com.bridgelabz.bookstoreapp.model.UserRegistrationData;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import com.bridgelabz.bookstoreapp.service.IUserRegistrationService;
import com.bridgelabz.bookstoreapp.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.lang.constant.Constable;
import java.util.List;
@RestController
@RequestMapping("/userregistration")
@Slf4j
public class UserResgistrationController {
    //Dependency injection
    @Autowired
    private IUserRegistrationService userRegistrationService;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    UserRegistrationRepository userRepository;
    //Create user data api
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> createBookStoreData(
            @Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        log.debug("User DTO" + userRegistrationDTO.toString());
        UserRegistrationData userRegistrationData = userRegistrationService.createBookStoreData(userRegistrationDTO);
        String token = tokenUtil.createToken(userRegistrationData.getUserId());
        ResponseDTO respDTO = new ResponseDTO("Created book store data for:",token);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    //get all user data api
    @GetMapping("/get/all")
    public List<UserRegistrationData> getBookStoreData() {
        List<UserRegistrationData> userRegistrationDataList = userRegistrationService.getBookStoreData();
        return userRegistrationDataList;
    }
    //get user data by id api
    @GetMapping("/getall/{token}")
    public ResponseEntity<ResponseDTO> getBookStoreDataById(@PathVariable String token) {
        UserRegistrationData userRegistrationData = userRegistrationService.getBookStoreDataById(token);
        ResponseDTO respDTO = new ResponseDTO("Get Call Success for id:", userRegistrationData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    //user login api
    @PostMapping("/users/login")
    public Constable loginUser(@Valid @RequestBody UserRegistrationData user) {
        List<UserRegistrationData> users = userRepository.findAll();
        for (UserRegistrationData other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(true);
                ResponseDTO respDTO = new ResponseDTO("login successfully:", users);
                return Status.LOGIN_SUCCESS;
            }
        }
        ResponseDTO respDTO = new ResponseDTO("login failed:", users);
        return Status.LOGIN_FAILURE;
    }
    //user password change api
    @PutMapping("/changepassword")
    public ResponseEntity<ResponseDTO> changeUserPassword(@RequestHeader String token,
                                                                 @Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserRegistrationData userRegistrationData = userRegistrationService.changeUserPassword(token, userRegistrationDTO);
        ResponseDTO respDTO = new ResponseDTO("Changed password for: ", userRegistrationData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    //get by mailid api
    @GetMapping("/get/{email}")
    public ResponseEntity<ResponseDTO> getbyEmailID(@PathVariable String email) {
        List<UserRegistrationData> userRegistrationDataList = null;
        userRegistrationDataList = userRegistrationService.getbyEmailID(email);
        ResponseDTO response = new ResponseDTO("Get Call for Get by Email ID Successful", userRegistrationDataList);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }
    //update user api
    @PutMapping("/updateuser")
    public ResponseEntity<ResponseDTO> updateUserData(@RequestHeader String token,
                                                                 @Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserRegistrationData userRegistrationData = userRegistrationService.updateUserData(token, userRegistrationDTO);
        ResponseDTO respDTO = new ResponseDTO("Updated User Data for: ", userRegistrationData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
}