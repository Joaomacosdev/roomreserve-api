package br.com.roomreserve.service.validator;

import br.com.roomreserve.infra.exception.SalaInvalidaException;
import br.com.roomreserve.model.Sala;
import org.springframework.stereotype.Component;

@Component
public class SalaValidator {

    public void validaSala(Sala sala){
        if (sala.getNome() == null || sala.getNome().isEmpty()) {
            throw new SalaInvalidaException("Nome não pode ser nulo nem vazio");
        }
    }
}
