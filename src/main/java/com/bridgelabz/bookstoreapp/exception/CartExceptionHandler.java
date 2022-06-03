package com.bridgelabz.bookstoreapp.exception;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;

@ControllerAdvice
@Slf4j
public class CartExceptionHandler {
    private static final String message = "Exception While Processing REST Request";

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDTO> handelHttpMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        log.error("Invalid Date Format", exception);
        ResponseDTO respDTO = new ResponseDTO(message, "Should have date in dd MMM yyyy format");
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errMesg = errorList.stream().map(objErr -> objErr.getDefaultMessage())
                .collect(Collectors.toList());
        ResponseDTO respDTO = new ResponseDTO("Exception While Processing REST Request", errMesg);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserRegistrationDataException.class)
    public ResponseEntity<ResponseDTO> handleEmployeePayrollException(UserRegistrationDataException exception) {
        ResponseDTO respDTO = new ResponseDTO("Exception while REST Request", exception.getMessage());
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.BAD_REQUEST);
    }
}

