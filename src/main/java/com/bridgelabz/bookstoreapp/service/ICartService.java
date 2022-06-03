package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.model.CartData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface ICartService {
    //Defind service layer method declaration
    CartData insertCartData(@RequestBody CartDTO cartDTO);

    List<CartData> getCartData();

    CartData getCartDataById(String token);
    void deleteCartData(String token);
    CartData updateCartbyID(@RequestHeader String token, @RequestBody CartDTO cartDTO);

    CartData updateQuantity(@RequestHeader String token, @RequestBody CartDTO cartDTO);

}
