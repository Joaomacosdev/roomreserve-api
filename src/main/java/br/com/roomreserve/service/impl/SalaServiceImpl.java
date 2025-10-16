package br.com.roomreserve.service.impl;

import br.com.roomreserve.dto.request.SalaRequestDTO;
import br.com.roomreserve.dto.response.SalaResponseDTO;
import br.com.roomreserve.model.Sala;
import br.com.roomreserve.repository.SalaRepository;
import br.com.roomreserve.service.SalaService;
import br.com.roomreserve.service.validator.SalaValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SalaServiceImpl implements SalaService {

    private final SalaRepository salaRepository;
    private final SalaValidator salaValidator;

    public SalaServiceImpl(SalaRepository salaRepository, SalaValidator salaValidator) {
        this.salaRepository = salaRepository;
        this.salaValidator = salaValidator;
    }

    @Override
    @Transactional
    public SalaResponseDTO cadastrarSala(SalaRequestDTO requestDTO) {

        Sala sala = new Sala(requestDTO);

        salaValidator.validaSala(sala);

        sala = salaRepository.save(new Sala(requestDTO));
        return new SalaResponseDTO(sala);
    }


}
