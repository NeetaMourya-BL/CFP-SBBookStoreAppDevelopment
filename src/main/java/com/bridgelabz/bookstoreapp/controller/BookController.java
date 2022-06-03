package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserRegistrationDTO;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.model.UserRegistrationData;
import com.bridgelabz.bookstoreapp.service.IBookService;
import com.bridgelabz.bookstoreapp.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {
    //Dependency injection
    @Autowired
    private IBookService bookService;

    @Autowired
    private TokenUtil tokenUtil;
    //insert data into book
    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertBookStoreData(
            @Valid @RequestBody BookDTO bookDTO) {
        log.debug("Book DTO" + bookDTO.toString());
        BookData bookData = bookService.insertBookData(bookDTO);
        String token = tokenUtil.createToken((int) bookData.getId());
        ResponseDTO respDTO = new ResponseDTO("Created book data for:",token);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    //get all book data
    @GetMapping("/get/all")
    public List<BookData> getBookData() {
        List<BookData> bookDataList  = bookService.getBookData();
        return bookDataList;
    }
    //get book data by id
    @GetMapping("/getall/{token}")
    public ResponseEntity<ResponseDTO> getBookDataById(@PathVariable String token) {
        BookData bookData = null;
        bookData = bookService.getBookDataById(String.valueOf(token));
        ResponseDTO respDTO = new ResponseDTO("Get Call Success for id:", bookData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    //delete book data
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteBookData(@RequestHeader String token) {
        bookService.deleteBookData(token);
        ResponseDTO respDTO = new ResponseDTO("Delete Call Success for id: ", "bookId " + tokenUtil.decodeToken(token));
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    //search book by name
    @GetMapping("/get/{token}")
    public ResponseEntity<ResponseDTO> searchbyBookName(@PathVariable String token) {
        BookData bookData = null;
        bookData = (BookData) bookService.searchbyBookName(String.valueOf(token));
        ResponseDTO respDTO = new ResponseDTO("Get Call for search by Book Name Successful:", bookData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    //update book data by id
    @PutMapping("/updateBookbyID")
    public ResponseEntity<ResponseDTO> updateBookbyID(@RequestHeader String token,
                                                      @Valid @RequestBody BookDTO bookDTO) {
        BookData bookData = bookService.updateBookbyID(token, bookDTO);
        ResponseDTO respDTO = new ResponseDTO("updated data for Book by ID: ", bookData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    //arrnage book quantity in asending order
    @GetMapping("/sortingAsce/{quantity}")
    public ResponseEntity<ResponseDTO> sortingAsce(@PathVariable String quantity) {
        List<BookData> bookDataList = null;
        bookDataList = bookService.sortingAsce(quantity);
        ResponseDTO response = new ResponseDTO("Get Call for sorting Asce by quantity Successful", bookDataList);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }
    //arrnage book quantity in desending order
    @GetMapping("sortingDesc/{quantity}")
    public ResponseEntity<ResponseDTO> sortingDesc(@PathVariable String quantity) {
        List<BookData> bookDataList = null;
        bookDataList = bookService.sortingDesc(quantity);
        ResponseDTO response = new ResponseDTO("Get Call for sorting Desc by quantity Successful", bookDataList);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }
    //update book data quantity by token
    @PutMapping("/updateQuantity")
    public ResponseEntity<ResponseDTO> updateQuantity(@RequestHeader String token,
                                                      @Valid @RequestBody BookDTO bookDTO) {
        BookData bookData = bookService.updateQuantity(token, bookDTO);
        ResponseDTO respDTO = new ResponseDTO("update book quantity for: ", bookData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
}

