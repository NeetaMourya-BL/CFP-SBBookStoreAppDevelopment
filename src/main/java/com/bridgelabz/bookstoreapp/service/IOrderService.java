package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.OrderData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface IOrderService {
    //Defind service layer method declaration
    OrderData insertOrderData(@RequestBody OrderDTO orderDTO);

    List<OrderData> getOrderData();

    OrderData getOrderDataById(String token);

    ResponseDTO cancelOrderbyID(long orderId, long userId);
}
