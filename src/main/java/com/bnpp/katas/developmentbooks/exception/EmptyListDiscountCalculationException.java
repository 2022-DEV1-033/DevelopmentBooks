package com.bnpp.katas.developmentbooks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyListDiscountCalculationException extends RuntimeException {
    public EmptyListDiscountCalculationException(){
        super("given list of books is null or empty");
    }
}
