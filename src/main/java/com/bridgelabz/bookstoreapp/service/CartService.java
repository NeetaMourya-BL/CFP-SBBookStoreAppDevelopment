package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.exception.BookException;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.model.CartData;
import com.bridgelabz.bookstoreapp.model.UserRegistrationData;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.CartRepository;
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
public class CartService implements ICartService {

    //inside this layer we communication between a controller and repository layer
    //Dependency injection
    @Autowired
    BookRepository bookRepository;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    private EmailListener emaillistner;

    @Autowired
    private EmailSenderService sender;
    @Override
    public CartData insertCartData(CartDTO cartDTO) {
        Optional<BookData> book = bookRepository.findById(cartDTO.getBookid());
        Optional<UserRegistrationData> userRegistration = userRegistrationRepository.findById(cartDTO.getUserid());
        if (book.isPresent() && userRegistration.isPresent()) {
            CartData newCart = new CartData(cartDTO.getQuantity(), book.get(), userRegistration.get());
            cartRepository.save(newCart);
            sender.sendEmail("neeta.mourya@bridgelabz.com", "Test Email", "Cart data added SuccessFully");
            return newCart;
        } else {
            throw new BookException("Book or User doesn't exists ");
        }
    }

    @Override
    public List<CartData> getCartData() {
        return cartRepository.findAll();
    }

    @Override
    public CartData getCartDataById(String token) {
        long id = tokenUtil.decodeToken(token);
        Optional<CartData> cartData = cartRepository.findById(id);
        return cartData.get();
    }
    @Override
    public void deleteCartData(String token) {
        CartData cartData = this.getCartDataById(token);
        cartRepository.delete(cartData);
      }

      @Override
        public CartData updateCartbyID(String token, CartDTO cartDTO) {
            CartData cartData = this.getCartDataById(token);
            cartData.updateCartbyID(cartDTO);
            return cartRepository.save(cartData);
        }
    @Override
    public CartData updateQuantity(String token, CartDTO cartDTO) {
        CartData cartData = this.getCartDataById(token);
        cartData.updateCartbyID(cartDTO);
        return cartRepository.save(cartData);
    }

}
