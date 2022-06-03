package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.exception.BookException;
import com.bridgelabz.bookstoreapp.exception.UserRegistrationDataException;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.model.OrderData;
import com.bridgelabz.bookstoreapp.model.UserRegistrationData;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.OrderRepository;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
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
public class OrderService implements IOrderService {

    //inside this layer we communication between a controller and repository layer
    //Dependency injection
    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    private EmailListener emaillistner;

    @Autowired
    private EmailSenderService sender;
    @Override
    public OrderData insertOrderData(OrderDTO orderDTO) {
        Optional<BookData> book = bookRepository.findById(orderDTO.getBookid());
        Optional<UserRegistrationData> user = userRegistrationRepository.findById(orderDTO.getUserid());
        if (book.isPresent() && user.isPresent()) {
            if (orderDTO.getQuantity()<= book.get().getQuantity()) {
                int quantity = book.get().getQuantity()-orderDTO.getQuantity();
                book.get().setQuantity(quantity);
                bookRepository.save(book.get());
                OrderData newOrder = new OrderData(book.get().getPrice(), orderDTO.getQuantity(), orderDTO.getAddress(), book.get(), user.get(), orderDTO.isCancel());
                orderRepository.save(newOrder);
                log.info("Order record inserted successfully");
                sender.sendEmail("neeta.mourya@bridgelabz.com", "Test Email", "Order placed successfully");
                return newOrder;
            } else {
                throw new BookException("Requested quantity is out of stock");
            }
        } else {
            throw new BookException("Book or User doesn't exists");
        }
    }

    @Override
    public List<OrderData> getOrderData() {
        return orderRepository.findAll();
    }

    @Override
    public OrderData getOrderDataById(String token) {
        System.out.println(token);
        System.out.println(tokenUtil.decodeToken(token));
        return orderRepository.findById(tokenUtil.decodeToken(token))
                .orElseThrow(() -> new UserRegistrationDataException("User With orderId: " + tokenUtil.decodeToken(token) + " does not exists"));
    }
    @Override
    public ResponseDTO cancelOrderbyID(long orderId, long userId) {
        Optional<OrderData> order = orderRepository.findById(Long.valueOf(orderId));
        if (order.isPresent() && userId == order.get().getUser().getUserId())
        {
            order.get().setCancle(true);
            orderRepository.save(order.get());
            return new ResponseDTO("Order is cancelled successfully", order);
        } else {
            return new ResponseDTO("Order can not be canceled", "userId or orderId is incorrect");
        }
    }
}
