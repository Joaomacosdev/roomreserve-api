package br.com.roomreserve.service.validator;

import br.com.roomreserve.dto.request.SalaRequestDTO;
import br.com.roomreserve.infra.exception.ReservaInvalidaException;
import br.com.roomreserve.model.Sala;
import br.com.roomreserve.repository.ReservaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservaValidatorTest {


    @Test
    void cenarioValidarReserva() {

        ReservaRepository reservaRepository = Mockito.mock(ReservaRepository.class);

        ReservaValidator reservaValidator = new ReservaValidator(reservaRepository);

        SalaRequestDTO requestDTO = new SalaRequestDTO("João", 10, false);
        Sala sala = new Sala(requestDTO);

        LocalDate inicio = LocalDate.now().plusDays(3);
        LocalDate fim = LocalDate.now().plusDays(2);

        Assertions.assertThrows(ReservaInvalidaException.class, () -> {
            reservaValidator.validarReserva(sala, inicio, fim);
        });
    }

    @Test
    void cenarioVerificarConflito() {
        ReservaRepository reservaRepository = Mockito.mock(ReservaRepository.class);

        SalaRequestDTO requestDTO = new SalaRequestDTO("João", 10, true);
        Sala sala = new Sala(requestDTO);
        LocalDate inicio = LocalDate.now().plusDays(3);
        LocalDate fim = LocalDate.now().plusDays(4);

        Mockito.when(reservaRepository.existsBySalaAndInicioLessThanAndFimGreaterThan(
                sala, fim, inicio
        )).thenReturn(true);

        ReservaValidator reservaValidator = new ReservaValidator(reservaRepository);

        assertThrows(ReservaInvalidaException.class, () -> {
            reservaValidator.verificarConflito(sala, inicio, fim);
        });
    }
}

