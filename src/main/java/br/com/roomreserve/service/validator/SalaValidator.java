package br.com.roomreserve.service.validator;

import br.com.roomreserve.infra.exception.NomeJaCadastradoException;
import br.com.roomreserve.infra.exception.ReservaInvalidaException;
import br.com.roomreserve.infra.exception.SalaInvalidaException;
import br.com.roomreserve.model.Sala;
import br.com.roomreserve.repository.SalaRepository;
import org.springframework.stereotype.Component;

@Component
public class SalaValidator {

    private final SalaRepository salaRepository;

    public SalaValidator(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public void validaSala(Sala sala){
        if (sala.getNome() == null || sala.getNome().isEmpty()) {
            throw new SalaInvalidaException("Nome não pode ser nulo nem vazio");
        }
    }

    public void validarSalaComMesmoNome(Sala sala){
        if (salaRepository.existsByNome(sala.getNome())){
            throw new NomeJaCadastradoException("Sala com o nome: " + sala.getNome() + " já existe");
        }
    }

    public void validarCapacidade(Sala sala) {
        if (sala.getCapacidade() <= 0) {
            throw new ReservaInvalidaException(
                    "A sala não possui mais vagas disponíveis. Capacidade atual: " +sala.getCapacidade()
            );
        }
    }

    public void reduzirCapacidade(Sala sala) {
        if (sala.getCapacidade() == null) {
            throw new IllegalStateException("Capacidade da sala não definida.");
        }

        if (sala.getCapacidade() <= 0) {
            throw new ReservaInvalidaException("A sala está lotada. Não é possível reservar mais lugares.");
        }

        sala.setCapacidade(sala.getCapacidade() - 1);
    }
}
