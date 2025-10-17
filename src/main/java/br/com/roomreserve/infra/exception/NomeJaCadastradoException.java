package br.com.roomreserve.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NomeJaCadastradoException extends RuntimeException{
    public NomeJaCadastradoException(String message) {
        super(message);
    }
}
