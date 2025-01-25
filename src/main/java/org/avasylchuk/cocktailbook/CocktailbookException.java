package org.avasylchuk.cocktailbook;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CocktailbookException extends RuntimeException {
    public CocktailbookException(String message) {
        super(message);
    }
}
