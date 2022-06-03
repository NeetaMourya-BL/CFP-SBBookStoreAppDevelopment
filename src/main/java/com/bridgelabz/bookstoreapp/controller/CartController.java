package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.CartData;
import com.bridgelabz.bookstoreapp.service.ICartService;
import com.bridgelabz.bookstoreapp.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {
    //Dependency injection
    @Autowired
    private ICartService cartService;

    @Autowired
    private TokenUtil tokenUtil;
    //insert cart data
    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertCartData(
            @Valid @RequestBody CartDTO cartDTO) {
        log.debug("Cart DTO" + cartDTO.toString());
        CartData cartData = cartService.insertCartData(cartDTO);
        String token = tokenUtil.createToken((int) cartData.getCartid());
        ResponseDTO respDTO = new ResponseDTO("Created Cart data for:",token);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    //get all cart data
    @GetMapping("/get/all")
    public List<CartData> getCartData() {
        List<CartData> cartDataList  = cartService.getCartData();
        return cartDataList;
    }
    //get all cart data by token
    @GetMapping("/getall/{token}")
    public ResponseEntity<ResponseDTO> getCartDataById(@PathVariable String token) {
        CartData cartData = null;
        cartData = cartService.getCartDataById(token);
        ResponseDTO respDTO = new ResponseDTO("Get Call Success for id:", cartData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    //delete cart data
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCartData(@RequestHeader String token) {
        cartService.deleteCartData(token);
        ResponseDTO respDTO = new ResponseDTO("Delete Call Success for id: ", "cartId " + tokenUtil.decodeToken(token));
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    //update cart data by id
    @PutMapping("/updateCartbyID")
    public ResponseEntity<ResponseDTO> updateCartbyID(@RequestHeader String token,
                                                      @Valid @RequestBody CartDTO cartDTO) {
        CartData cartData = cartService.updateCartbyID(token, cartDTO);
        ResponseDTO respDTO = new ResponseDTO("updated data for cart by ID: ", cartData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    //update book quantity by token
    @PutMapping("/updateQuantity")
    public ResponseEntity<ResponseDTO> updateQuantity(@RequestHeader String token,
                                                      @Valid @RequestBody CartDTO cartDTO) {
        CartData cartData = cartService.updateQuantity(token, cartDTO);
        ResponseDTO respDTO = new ResponseDTO("update book quantity for: ", cartData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
}