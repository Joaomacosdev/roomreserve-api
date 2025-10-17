package br.com.roomreserve.service.impl;

import br.com.roomreserve.dto.request.SalaRequestDTO;
import br.com.roomreserve.dto.request.SalaUpdateRequestDTO;
import br.com.roomreserve.dto.response.SalaResponseDTO;
import br.com.roomreserve.infra.exception.NotFoundException;
import br.com.roomreserve.model.Sala;
import br.com.roomreserve.repository.SalaRepository;
import br.com.roomreserve.service.SalaService;
import br.com.roomreserve.service.validator.SalaValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

        salaValidator.validarSalaComMesmoNome(sala);
        salaValidator.validaSala(sala);

        sala = salaRepository.save(new Sala(requestDTO));
        return new SalaResponseDTO(sala);
    }

    @Override
    @Transactional(readOnly = true)
    public SalaResponseDTO buscarPorId(Long id) {
        var sala = salaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Sala com id: " + id + " não encontrada"));
        return new SalaResponseDTO(sala);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SalaResponseDTO> listarTodasSalas(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return salaRepository.findAllByAtivoTrue(pageable).map(SalaResponseDTO::new);
    }

    @Override
    @Transactional
    public SalaResponseDTO atualizarDadosSala(SalaUpdateRequestDTO requestDTO) {
        var sala = salaRepository.findById(requestDTO.id()).orElseThrow(
                () -> new NotFoundException("Sala com id: " + requestDTO.id() + " não encontrada")
        );
        sala.atualizarDados(requestDTO);
        sala = salaRepository.save(sala);
        return new SalaResponseDTO(sala);
    }

    @Override
    @Transactional
    public SalaResponseDTO ativarSala(Long id) {
        var sala = salaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Sala com id: " + id + " não encontrada")
        );
        sala.ativarSala();

        return new SalaResponseDTO(sala);
    }

    @Override
    @Transactional
    public void desativarSala(Long id) {
        var sala = salaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Sala com id: " + id + " não encontrada")
        );
        sala.desativarSala();
    }




}
