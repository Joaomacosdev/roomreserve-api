package br.com.roomreserve.service.validator;

import br.com.roomreserve.infra.exception.ReservaInvalidaException;
import br.com.roomreserve.model.Sala;
import br.com.roomreserve.repository.ReservaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ReservaValidator {


    private final ReservaRepository reservaRepository;



    public ReservaValidator(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public void validarReserva(Sala sala, LocalDate inicio, LocalDate fim) {
        if (!sala.isAtivo()) {
            throw new ReservaInvalidaException("Não é possível reservar uma sala inativa.");
        }

        if (inicio.isAfter(fim) || inicio.isEqual(fim)) {
            throw new ReservaInvalidaException("A data de início deve ser anterior à data de fim da reserva.");
        }

        if (sala.getCapacidade() <= 0) {
            throw new ReservaInvalidaException("A sala está lotada. Não há capacidade disponível.");
        }
    }

    public void verificarConflito(Sala sala, LocalDate inicio, LocalDate fim) {
        boolean conflito = reservaRepository.existsBySalaAndInicioLessThanAndFimGreaterThan(
                sala,
                fim,
                inicio
        );

        if (conflito) {
            throw new ReservaInvalidaException(
                    "Conflito: a sala já possui uma reserva nesse intervalo."
            );
        }
    }
}
