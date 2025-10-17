package br.com.roomreserve.service.validator;

import br.com.roomreserve.dto.request.SalaRequestDTO;
import br.com.roomreserve.infra.exception.NomeJaCadastradoException;
import br.com.roomreserve.infra.exception.ReservaInvalidaException;
import br.com.roomreserve.infra.exception.SalaInvalidaException;
import br.com.roomreserve.model.Sala;
import br.com.roomreserve.repository.ReservaRepository;
import br.com.roomreserve.repository.SalaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalaValidatorTest {

    @Mock
    private SalaRepository salaRepository;

    @InjectMocks
    private SalaValidator salaValidator;

    private SalaRequestDTO requestDTO;

    @BeforeEach
    void setup() {
        requestDTO = new SalaRequestDTO("Sala 1", 10, true);
    }

    @Test
    void deveLancarExcecaoQuandoNomeForNulo() {
        SalaRequestDTO dto = new SalaRequestDTO(null, 10, true);
        Sala sala = new Sala(dto);

        assertThrows(SalaInvalidaException.class, () -> salaValidator.validaSala(sala));
    }

    @Test
    void deveLancarExcecaoQuandoCapacidadeForInvalida() {
        SalaRequestDTO dto = new SalaRequestDTO("Sala Pequena", 0, true);
        Sala sala = new Sala(dto);

        assertThrows(SalaInvalidaException.class, () -> salaValidator.validaSala(sala));
    }

    @Test
    void deveLancarExcecaoQuandoNomeJaEstiverCadastrado() {
        Sala sala = new Sala(requestDTO);
        when(salaRepository.existsByNome(sala.getNome())).thenReturn(true);

        assertThrows(NomeJaCadastradoException.class, () -> salaValidator.validarSalaComMesmoNome(sala));
    }

    @Test
    void naoDeveLancarExcecaoQuandoSalaForValida() {
        Sala sala = new Sala(requestDTO);
        when(salaRepository.existsByNome(sala.getNome())).thenReturn(false);

        // Se nenhuma exceção for lançada, o teste passa
        salaValidator.validaSala(sala);
        salaValidator.validarSalaComMesmoNome(sala);
    }
}