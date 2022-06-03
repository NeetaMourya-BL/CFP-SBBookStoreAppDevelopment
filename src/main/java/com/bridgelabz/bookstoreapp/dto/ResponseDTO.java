package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

public @Data class ResponseDTO {
//getting response from server end in form of message and data
    private String message;
    private Object data;

    public ResponseDTO(String string, Object bookData) {
        this.message = string;
        this.data = bookData;
    }
}
