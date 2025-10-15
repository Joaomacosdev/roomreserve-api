package br.com.roomreserve.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReservaInvalidaException extends RuntimeException{
    public ReservaInvalidaException(String message) {
        super(message);
    }
}
