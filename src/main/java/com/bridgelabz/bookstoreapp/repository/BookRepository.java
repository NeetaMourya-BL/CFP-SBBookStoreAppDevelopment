package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.model.UserRegistrationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookData, Long> {
    //getting data using SQL
    @Query(value = "select * from bookdb order by quantity asc", nativeQuery = true)
    List<BookData> sortingAsce(String quantity);

    @Query(value = "select * from bookdb order by quantity desc", nativeQuery = true)
    List<BookData> sortingDesc(String quantity);
}

