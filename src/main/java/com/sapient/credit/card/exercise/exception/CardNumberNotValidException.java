package com.sapient.credit.card.exercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardNumberNotValidException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public CardNumberNotValidException(String message){
        super(message);
    }
}
