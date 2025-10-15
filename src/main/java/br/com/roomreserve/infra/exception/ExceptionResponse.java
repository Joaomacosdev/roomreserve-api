package br.com.roomreserve.infra.exception;

import java.util.Date;

public record ExceptionResponse(
        Date timestamp,
        String message,
        String details
) {
}
