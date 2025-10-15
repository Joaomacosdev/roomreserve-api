package br.com.roomreserve.service.impl;

import br.com.roomreserve.dto.request.SalaRequestDTO;
import br.com.roomreserve.dto.response.SalaResponseDTO;
import br.com.roomreserve.infra.exception.SalaInvalidaException;
import br.com.roomreserve.model.Sala;
import br.com.roomreserve.repository.SalaRepository;
import br.com.roomreserve.service.SalaService;
import org.springframework.stereotype.Service;

@Service
public class SalaServiceImpl implements SalaService {

    private final SalaRepository salaRepository;

    public SalaServiceImpl(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    @Override
    public SalaResponseDTO cadastrarSala(SalaRequestDTO requestDTO) {
        validaSala(requestDTO);

        var sala = salaRepository.save(new Sala(requestDTO));
        return new SalaResponseDTO(sala);
    }

    private void validaSala(SalaRequestDTO requestDTO){
        if (requestDTO.nome().isEmpty() && requestDTO.nome() == null){
            throw new SalaInvalidaException("nome não pode ser nulo e nem vazio");
        }
    }
}
