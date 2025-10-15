package br.com.roomreserve.service.impl;

import br.com.roomreserve.infra.exception.ReservaInvalidaException;
import br.com.roomreserve.model.Sala;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReservaService {

    private void validarReserva(Sala sala, LocalDate inicio, LocalDate fim) {
        if (!sala.isAtivo()) {
            throw new ReservaInvalidaException("Não é possível reservar uma sala inativa");

        }
        if (inicio.isAfter(fim) || inicio.isEqual(fim)) {
            throw new ReservaInvalidaException("Não é possível reservar uma sala inativa");

        }
        if (sala.getCapacidade() <= 0) {
            throw new ReservaInvalidaException("Não é possível reservar uma sala inativa");

        }
    }
}
