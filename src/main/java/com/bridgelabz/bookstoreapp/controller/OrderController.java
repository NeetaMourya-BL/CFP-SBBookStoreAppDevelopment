package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.*;
import com.bridgelabz.bookstoreapp.model.OrderData;
import com.bridgelabz.bookstoreapp.service.IOrderService;
import com.bridgelabz.bookstoreapp.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    //Dependency injection
    @Autowired
    private IOrderService orderService;

    @Autowired
    private TokenUtil tokenUtil;
    //insert order data
    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertOrderData(
            @Valid @RequestBody OrderDTO orderDTO) {
        log.debug("Order DTO" + orderDTO.toString());
        OrderData orderData = orderService.insertOrderData(orderDTO);
        System.out.println(orderData);
        String token = tokenUtil.createToken(orderData.getOrderID());
        ResponseDTO respDTO = new ResponseDTO("Created order data for:", token);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    //get all order data
    @GetMapping("/get/all")
    public List<OrderData> getOrderData() {
        List<OrderData> orderDataList = orderService.getOrderData();
        return orderDataList;
    }
    //get all order data by token
    @GetMapping("/getall/{token}")
    public ResponseEntity<ResponseDTO> getOrderDataById(@PathVariable String token) {
        OrderData orderData = null;
        orderData = orderService.getOrderDataById(String.valueOf(token));
        ResponseDTO respDTO = new ResponseDTO("Get Call Success for id:", orderData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    //cancel order data
    @PutMapping("/cancel/{orderId}")
    public ResponseEntity cancelOrderbyID(@PathVariable long orderId,@RequestHeader long userId ){
        ResponseDTO responseDTO = orderService.cancelOrderbyID(orderId,userId);
        return new ResponseEntity(responseDTO,HttpStatus.OK);
    }
}