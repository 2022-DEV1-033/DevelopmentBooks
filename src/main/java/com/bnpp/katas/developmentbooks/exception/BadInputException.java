package com.bnpp.katas.developmentbooks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadInputException extends RuntimeException {
    public BadInputException(){
        super("Bad Input (duplicate id or zero quantity)");
    }
}
