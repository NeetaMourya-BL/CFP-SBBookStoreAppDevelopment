package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.util.EmailListener;
import com.bridgelabz.bookstoreapp.util.EmailSenderService;
import com.bridgelabz.bookstoreapp.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService implements IBookService {

    //inside this layer we communication between a controller and repository layer
    //Dependency injection
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    private EmailListener emaillistner;

    @Autowired
    private EmailSenderService sender;
    @Override
    public BookData insertBookData(BookDTO bookDTO) {
        BookData bookData = new BookData(bookDTO);
//        emaillistner.sendMail();
        sender.sendEmail("neeta.mourya@bridgelabz.com", "Test Email", "inser book SuccessFully");
        return bookRepository.save(bookData);
    }

    @Override
    public List<BookData> getBookData() {
        return bookRepository.findAll();
    }

    @Override
    public BookData getBookDataById(String token) {
        long id = tokenUtil.decodeToken(token);
        Optional<BookData> bookData = bookRepository.findById(id);
        return bookData.get();
    }

    @Override
    public BookData getBookDataById(long id) {
        Optional<BookData> bookData = bookRepository.findById(id);
        return bookData.get();
    }

    @Override
    public void deleteBookData(String token) {
        BookData bookData = this.getBookDataById(token);
        bookRepository.delete(bookData);
    }

    @Override
    public BookData searchbyBookName(String token) {
        long id = tokenUtil.decodeToken(token);
        Optional<BookData> bookData = bookRepository.findById(id);
        return bookData.get();
    }

    @Override
    public BookData updateBookbyID(String token, BookDTO bookDTO) {
        BookData bookData = this.getBookDataById(token);
        System.out.println(token);
        bookData.updateBookbyID(bookDTO);
        return bookRepository.save(bookData);
    }

    @Override
    public List<BookData> sortingAsce(String quantity) {
        return bookRepository.sortingAsce(quantity);
    }

    @Override
    public List<BookData> sortingDesc(String quantity) {
        return bookRepository.sortingDesc(quantity);
    }

    @Override
    public BookData updateQuantity(String token, BookDTO bookDTO) {
        BookData bookData = this.getBookDataById(token);
        bookData.updateBookbyID(bookDTO);
        return bookRepository.save(bookData);
    }

}