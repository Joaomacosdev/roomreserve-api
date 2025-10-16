package br.com.roomreserve.service.validator;

import br.com.roomreserve.dto.request.SalaRequestDTO;
import br.com.roomreserve.infra.exception.ReservaInvalidaException;
import br.com.roomreserve.infra.exception.SalaInvalidaException;
import br.com.roomreserve.model.Sala;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaValidatorTest {

    @Test
    void cenarioValidaSala() {


        SalaValidator salaValidator = new SalaValidator();

        SalaRequestDTO requestDTO = new SalaRequestDTO(null, 10, false);
        Sala sala = new Sala(requestDTO);

        Assertions.assertThrows(SalaInvalidaException.class, () -> {
            salaValidator.validaSala(sala);
        });
    }
}