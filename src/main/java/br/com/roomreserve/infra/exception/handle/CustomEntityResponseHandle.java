package br.com.roomreserve.infra.exception.handle;

import br.com.roomreserve.infra.exception.ExceptionResponse;
import br.com.roomreserve.infra.exception.ReservaInvalidaException;
import br.com.roomreserve.infra.exception.SalaInvalidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class CustomEntityResponseHandle {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ReservaInvalidaException.class)
    public ResponseEntity<ExceptionResponse> reservaInvalidaException(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SalaInvalidaException.class)
    public ResponseEntity<ExceptionResponse> salaInvalidaException(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
