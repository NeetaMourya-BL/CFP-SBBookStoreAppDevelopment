package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.UserRegistrationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRegistrationRepository extends JpaRepository<UserRegistrationData, Long> {

    //getting data using SQL
    @Query(value = "select * from userregistrationdb where email= :email", nativeQuery = true)
    List<UserRegistrationData> getbyEmailID(String email);

    }