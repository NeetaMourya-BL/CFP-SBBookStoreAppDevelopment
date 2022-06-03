package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.UserRegistrationDTO;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.model.UserRegistrationData;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IBookService {
    //Defind service layer method declaration
    BookData insertBookData(@RequestBody BookDTO bookDTO);

    List<BookData> getBookData();

    BookData getBookDataById(String token);

    BookData getBookDataById(long id);

    void deleteBookData(String token);


    BookData searchbyBookName(String token);

    BookData updateBookbyID(String token, BookDTO bookDTO);

    List<BookData> sortingAsce(String quantity);

    List<BookData> sortingDesc(String quantity);

    BookData updateQuantity(String token, BookDTO bookDTO);

}
